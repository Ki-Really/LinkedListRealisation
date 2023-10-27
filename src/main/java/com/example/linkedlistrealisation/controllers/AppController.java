package com.example.linkedlistrealisation.controllers;

import com.example.linkedlistrealisation.UserFactory;
import com.example.linkedlistrealisation.dataStructures.LinkedListRealisation;
import com.example.linkedlistrealisation.interfaces.UserTypeInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontWeight;

import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class AppController {
    @FXML
    public TextField addToEndInput;
    @FXML
    public TextField addByIndexInput;
    @FXML
    public Menu menuType;
    @FXML
    public Label removeByIndexErrorLbl;
    @FXML
    public Label addByIndexErrorLbl;
    @FXML
    public Label addToEndErrorLbl;
    @FXML
    public Label findByIndexErrorLbl;
    public Label showLabel;
    public TextField addId;
    public TextField removeId;
    public TextField findId;
    public Label valueForFind;
    public Button clearBtn;
    public VBox vBox;
    public HBox hBox;
    @FXML
    Button addToEndBtn;

    @FXML
    Button addByIndexBtn;
    @FXML
    Button removeByIndexBtn;
    @FXML
    Button findByIndexBtn;



    private UserTypeInterface currentUserType;
    private final UserFactory userFactory = new UserFactory();
    StringBuilder stringBuilder;

    LinkedListRealisation<Object> linkedListRealisation;
    @FXML
    private void initialize(){
        disableManagingBtn();
        showTypes();
        chooseType();
    }


    @FXML
    private void addToEnd(){
        disableErrorLabels();
        String valueToAdd = addToEndInput.getText();
        if(valueToAdd.isEmpty()){
            addToEndErrorLbl.setVisible(true);
            addToEndErrorLbl.setText("Empty input!");
        }else{
            Object obj = currentUserType.parseValueDeser(valueToAdd);
            if(obj!= null){
                linkedListRealisation.add(obj);
                drawList();
            }else {
                addToEndErrorLbl.setVisible(true);
                addToEndErrorLbl.setText("Неверный формат!");
            }
            addToEndErrorLbl.setVisible(false);
        }
    }

    @FXML
    private void addByIndex(){
        disableErrorLabels();
        String valueToAdd = addByIndexInput.getText();
        String index = addId.getText();
        if(valueToAdd.isEmpty() || index.isEmpty()){
            addByIndexErrorLbl.setVisible(true);
            addByIndexErrorLbl.setText("Empty input!");
        }else{
            Object obj = currentUserType.parseValueDeser(valueToAdd);
            linkedListRealisation.addByIndex(Integer.parseInt(index),obj);
            drawList();
            addByIndexErrorLbl.setVisible(false);
        }
    }

    @FXML
    private void removeByIndex(){
        disableErrorLabels();
        String indexToRemove = removeId.getText();
        if(indexToRemove.isEmpty()){
            removeByIndexErrorLbl.setVisible(true);
            removeByIndexErrorLbl.setText("Empty input!");
        }else{
            linkedListRealisation.removeByIndex(Integer.parseInt(indexToRemove));
            drawList();
            addToEndErrorLbl.setVisible(false);
        }
    }

    @FXML
    private void findByIndex(){
        disableErrorLabels();

        String indexToFind = findId.getText();
        if(indexToFind.isEmpty()){
            findByIndexErrorLbl.setVisible(true);
            findByIndexErrorLbl.setText("Empty input!");
        }else{
            String str = linkedListRealisation.get(Integer.parseInt(indexToFind)).toString();
            valueForFind.setVisible(true);
            valueForFind.setText(str);
            findByIndexErrorLbl.setVisible(false);
        }
    }
    @FXML
    private void clearList(){
        this.linkedListRealisation.clear();
        drawList();
    }
    @FXML
    private void sortList(){
        Comparator<Object> comparator = currentUserType.getTypeComparator();
        linkedListRealisation.sort(comparator);
       drawList();
    }
    private void chooseType(){
            menuType.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    MenuItem item = (MenuItem)actionEvent.getTarget();

                    linkedListRealisation = new LinkedListRealisation<>();
                    drawList();
                    currentUserType = userFactory.getBuilderByName(item.getText());

                    for(MenuItem menuItem : menuType.getItems()){
                        menuItem.setDisable(false);
                    }
                    item.setDisable(true);
                    enableManagingBtn();
                }
            });
    }
    private void showTypes(){
        int i = 0;

        for(String s : userFactory.getTypeNameList()){
            MenuItem itemToAdd = new MenuItem(s);
            itemToAdd.setId("item"+i);
            menuType.getItems().add(itemToAdd);
            i++;
        }
    }
    //TODO сделать норм отображение списка, рандомную генерацию листа
    public void drawList(){


        hBox.getChildren().clear();
        int size = linkedListRealisation.getSize();
        for(int i=0; i<size;i++){
            String str = currentUserType.readValueSer(linkedListRealisation.get(i));
            Label label = new Label(str);
            label.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 24));
            hBox.getChildren().add(label);

        }
    }

    private void disableManagingBtn(){
            addToEndBtn.setDisable(true);
            addByIndexBtn.setDisable(true);
            removeByIndexBtn.setDisable(true);
            findByIndexBtn.setDisable(true);
    }
    private void enableManagingBtn(){
        addToEndBtn.setDisable(false);
        addByIndexBtn.setDisable(false);
        removeByIndexBtn.setDisable(false);
        findByIndexBtn.setDisable(false);
    }
    private void disableErrorLabels(){
        addToEndErrorLbl.setVisible(false);
        addByIndexErrorLbl.setVisible(false);
        removeByIndexErrorLbl.setVisible(false);
        findByIndexErrorLbl.setVisible(false);
    }
}
