package com.zeroWebAppSecurity.pages;

import com.zeroWebAppSecurity.models.AccountShowTransactionModel;
import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountActivityPage extends BasePage {

    private List<AccountShowTransactionModel> showTransaction;

    @FindBy(className = "table")
    private WebElement transactionsTableContainer;

    public AccountActivityPage(WebDriver driver) {
        super(driver);
        showTransaction = new ArrayList<>();
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new AccountActivityPage(driver);
    }

    public void readTransactions(){
        showTransaction = readTransactionModelTable(transactionsTableContainer);
    }

    private List<AccountShowTransactionModel> readTransactionModelTable(WebElement table) {
        List<AccountShowTransactionModel> accountModelList = new ArrayList<>();
        WebElement tableBody = waitAndFindElement(table, By.tagName("tbody"));
        List<WebElement> tableRows = waitAndFindElements(tableBody, By.xpath("tr"));
        for (WebElement tableRow : tableRows) {
            accountModelList.add(readAccountModelRow(tableRow));
        }
        return accountModelList;
    }

    public AccountShowTransactionModel readAccountModelRow(WebElement tableRow) {
        Optional<WebElement> dateColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(1)"));
        Optional<WebElement> descriptionColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(2)"));
        Optional<WebElement> depositColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(3)"));
        Optional<WebElement> withdrawalColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(4)"));
        return AccountShowTransactionModel.builder()
                .date(dateColumn.isPresent() ? waitAndFindElement(dateColumn.get(), By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div[2]/table/tbody/tr[1]/td[1]")).getText() : null)
                .description(dateColumn.isPresent() ? waitAndFindElement(descriptionColumn.get(), By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div[2]/table/tbody/tr[1]/td[2]")).getText() : null)
                .deposit(dateColumn.isPresent() ? waitAndFindElement(depositColumn.get(), By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div[2]/table/tbody/tr[1]/td[3]")).getText() : null)
                .withdrawal(dateColumn.isPresent() ? waitAndFindElement(withdrawalColumn.get(), By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div[2]/table/tbody/tr[1]/td[4]")).getText() : null)
                .build();
    }
    public Optional<AccountShowTransactionModel> findAccountByDateAndModel(String date) {
            return findAccountByDate(date, showTransaction);
    }

    private Optional<AccountShowTransactionModel> findAccountByDate(String transactionDate, List<AccountShowTransactionModel> accountModelList) {
        return accountModelList.stream().filter(accountModel -> transactionDate.equals(accountModel.getDate()))
                .findFirst();
    }

    private Optional<WebElement> findColumnByLocator(WebElement row, By by) {
        return checkAndGetIfElementIsPresent(row, by);
    }

}
