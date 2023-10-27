module com.example.linkedlistrealisation {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.linkedlistrealisation to javafx.fxml;
    exports com.example.linkedlistrealisation;
    exports com.example.linkedlistrealisation.userTypes;
    opens com.example.linkedlistrealisation.userTypes to javafx.fxml;
    exports com.example.linkedlistrealisation.interfaces;
    opens com.example.linkedlistrealisation.interfaces to javafx.fxml;
    exports com.example.linkedlistrealisation.dataStructures;
    opens com.example.linkedlistrealisation.dataStructures to javafx.fxml;
    exports com.example.linkedlistrealisation.controllers;
    opens com.example.linkedlistrealisation.controllers to javafx.fxml;
}