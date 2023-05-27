package Controllers;

import Model.Account;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;

    @FXML
    private TextField accountNumberTxt;

    @FXML
    private TextField balanceTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField creationDateTxt;

    @FXML
    private TextField currencyTxt;

    @FXML
    private Button updateAccountBtn;

    @FXML
    private TextField userIDTxt;

    @FXML
    private TextField usernameTF;

    @FXML
    void cancelCreation(ActionEvent event) {
        Controllers.AccountsManagmentController.updateStage.close();

    }

    @FXML
    void updateAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
      int userID =  Integer.parseInt(userIDTxt.getText());
        String username = usernameTF.getText();
        int accountNumber = Integer.parseInt(accountNumberTxt.getText());
        String currency = currencyTxt.getText();
        Double balance = Double.parseDouble(balanceTxt.getText());
String creationDate = creationDateTxt.getText();
        Account newAccount = new Account(userID,accountNumber,username,currency,balance,creationDate);
        
        //set the new user id the same as the old user
        newAccount.setId(oldAccount.getId());
        
        // update the user in database by update method
        newAccount.update();
        
        //close the update stage using our global stage var in UsersManagmentController and show an alert
        Controllers.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //get selected user data from UsersManagmentController updatedUser var
        this.oldAccount = Controllers.AccountsManagmentController.selectedAccountToUpdate;

        //set text field's data the same as updatedUser data
        userIDTxt.setText(String.valueOf(oldAccount.getUser_id()));
        usernameTF.setText(oldAccount.getUsername());
        accountNumberTxt.setText(String.valueOf(oldAccount.getAccount_number()));
        balanceTxt.setText(String.valueOf(oldAccount.getBalance()));
        creationDateTxt.setText(String.valueOf(oldAccount.getCreation_date()));
        currencyTxt.setText((oldAccount.getCurrency()));

      
    }

}
