package application;
	
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Main extends Application {
	//Observable list only used for display purposes. 
	public static ObservableList <String> listOfNames = FXCollections.observableArrayList();
	
	BinaryTree firstNameTree = new BinaryTree("firstName");
	BinaryTree lastNameTree = new BinaryTree("lastName");
	BinaryTree ageOrderTree = new BinaryTree("ageOrder");
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
		//Left side buttons
			Text text01 = new Text("Number of people to");
			Text text001 = new Text("add to list (Press Enter):");
			TextField textBox01 = new TextField ("10");
			textBox01.setPrefSize(120,20);
			//First Name Binary Tree
			Text text1 = new Text("First Name Binary Tree:");
			MenuItem button01 = new MenuItem("Pre-Order Tree");
			MenuItem button001 = new MenuItem("In-Order Tree");
			MenuItem button0001 = new MenuItem("Post-Order Tree");
			MenuButton button1 = new MenuButton ("First Name Tree",null,button01,button001,button0001);
			button1.setPrefSize(130, 20);
			Button button2 = new Button("List by First Name");
			button2.setPrefSize(130, 20);
			//Last Name Binary Tree
			Text text2 = new Text("Last Name Binary Tree:");
			MenuItem button03 = new MenuItem("Pre-Order Tree");
			MenuItem button003 = new MenuItem("In-Order Tree");
			MenuItem button0003 = new MenuItem("Post-Order Tree");
			MenuButton button3 = new MenuButton ("Last Name Tree",null,button03,button003,button0003);
			button3.setPrefSize(130, 20);
			Button button4 = new Button("List by Last Name");
			button4.setPrefSize(130, 20);
			//Age Order Binary Tree
			Text text3 = new Text("Age Order Binary Tree:");
			MenuItem button05 = new MenuItem("Pre-Order Tree");
			MenuItem button005 = new MenuItem("In-Order Tree");
			MenuItem button0005 = new MenuItem("Post-Order Tree");
			MenuButton button5 = new MenuButton ("Age Order Tree",null,button05,button005,button0005);
			button5.setPrefSize(130, 20);
			Button button6 = new Button("List by Age Order");
			button6.setPrefSize(130, 20);
			//Searching buttons
			Text text4 = new Text("Search for person or ");
			Text text5 = new Text("people (press Search): ");
			//Search person
			TextField textBox7 = new TextField ("First Name");
			textBox7.setPrefSize(120,20);
			TextField textBox07 = new TextField ("Last Name");
			textBox07.setPrefSize(120,20);
			TextField textBox007 = new TextField ("Age");
			textBox007.setPrefSize(120,20);
			Button button07 = new Button("Search");
			button07.setPrefSize(130, 20);
			//Search by name length 
			Text text6 = new Text("Search minimum name");
			Text text7 = new Text("length (press Enter):");
			TextField textBox0007 = new TextField ("5");
			textBox0007.setPrefSize(120,20);
			//Display first name length > last name length and vice versa
			MenuItem menu7 = new MenuItem("First Name Length > Last Name Length");
			MenuItem menu07 = new MenuItem("First Name Length < Last Name Length");
			MenuButton menu007 = new MenuButton ("Name Length Lists",null,menu7,menu07);
			
//			Button button7 = new Button("Name Length > Last Name");
//			button7.setPrefSize(130, 20);
//			Button button8 = new Button("Search by Last Name");
//			button8.setPrefSize(130, 20);
//			Button button9 = new Button("Search by Age");
//			button9.setPrefSize(130, 20);

			
		//Organizing all the left side buttons
			VBox leftPane = new VBox(text01,text001,textBox01,text1,button1,button2,
					text2,button3,button4,text3,button5,button6,
					text4,text5,textBox7,textBox07,textBox007,button07,text6,text7,textBox0007,menu007
					//button7,button8,button9	Buttons not needed at the moment
					);
			leftPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			leftPane.setAlignment(Pos.TOP_LEFT);
			leftPane.setPadding(new Insets(25,25,25,25));
			root.setLeft(leftPane);
			
		//Right listView displaying names 
			Label rightTitle = new Label("List");
			rightTitle.setTranslateX(8);
			ListView <String> nameListView = new ListView <String>(listOfNames);
			nameListView.setPrefHeight(600);
			VBox rightPane = new VBox (rightTitle,nameListView);
			rightPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			root.setRight(rightPane);			
			
		//Actions of the left side buttons
			textBox01.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					int numberOfPeople = Integer.parseInt(textBox01.getText());
					ReadFile rf = new ReadFile(numberOfPeople);
					for (int i = 0; i < rf.personList.size(); i++) {
						firstNameTree.insertNode(rf.personList.get(i));
						lastNameTree.insertNode(rf.personList.get(i));
						ageOrderTree.insertNode(rf.personList.get(i));
					//When adding new people, will print off list of firstname order
						listOfNames.clear();
						System.out.println("First Name Sort:");
						rightTitle.setText("First Name Sort: ");
						firstNameTree.inOrder();
						System.out.println("------");	
					}
				}
			});
			
		//Buttons for sorting 
			//Button for first name sort 
			button2.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("First Name Sort:");
					rightTitle.setText("First Name Sort: ");
					firstNameTree.inOrder();
					System.out.println("------");
				}
			});
			//Button for last name sort
			button4.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Last Name Sort:");
					rightTitle.setText("Last Name Sort: ");
					lastNameTree.inOrder();
					System.out.println("------");
				}
			});
			//Button for age sort
			button6.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Age Order Sort:");
					rightTitle.setText("Age Order Sort: ");
					ageOrderTree.inOrder();
					System.out.println("------");
				}
			});
		//Buttons for Pre/In/PostOrder tree display lists
		//First Names
			//Pre-order first name
			button01.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Pre-order First Name Tree:");
					rightTitle.setText("Pre-order First Name Tree:");
					firstNameTree.printPreOrderBinaryTree(firstNameTree.root, "");
					System.out.println("------");
				}
			});
			//In-order first name
			button001.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("In-Order First Name Tree:");
					rightTitle.setText("In-order First Name Tree:");
					firstNameTree.printInBinaryTree(firstNameTree.root, "");
					System.out.println("------");
				}
			});
			//Post-order first name 
			button0001.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Post-Order First Name Tree:");
					rightTitle.setText("Post-order First Name Tree:");
					firstNameTree.printPostOrderBinaryTree(firstNameTree.root, "");
					System.out.println("------");
				}
			});
		//Last Names
			//Pre-order last name
			button03.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Pre-order Last Name Tree:");
					rightTitle.setText("Pre-order Last Name Tree:");
					lastNameTree.printPreOrderBinaryTree(lastNameTree.root, "");
					System.out.println("------");
				}
			});
			//In-order last name
			button003.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("In-Order Last Name Tree:");
					rightTitle.setText("In-Order Last Name Tree:");
					lastNameTree.printInBinaryTree(lastNameTree.root, "");
					System.out.println("------");
				}
			});
			//Post-order last name 
			button0003.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Post-Order Last Name Tree:");
					rightTitle.setText("Post-Order Last Name Tree:");
					lastNameTree.printPostOrderBinaryTree(lastNameTree.root, "");
					System.out.println("------");
				}
			});
		//Age order
			//Pre-order age order
			button05.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Pre-order Age Tree:");
					rightTitle.setText("Pre-order Age Tree:");
					ageOrderTree.printPreOrderBinaryTree(ageOrderTree.root, "");
					System.out.println("------");
				}
			});
			//In-order age order
			button005.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("In-Order Age Tree:");
					rightTitle.setText("In-Order Age Tree:");
					ageOrderTree.printInBinaryTree(ageOrderTree.root, "");
					System.out.println("------");
				}
			});
			//Post-order age order
			button0005.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Post-Order Age Tree:");
					rightTitle.setText("Post-Order Age Tree:");
					ageOrderTree.printPostOrderBinaryTree(ageOrderTree.root, "");
					System.out.println("------");
				}
			});
			//Search button
			button07.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle (ActionEvent arg0) {
					listOfNames.clear();
					System.out.println("Name Search:");
					rightTitle.setText("Name Search:");
					
					String firstNameSearch = textBox7.getText();
//					if (!firstNameSearch.equals("First Name")) {
//						System.out.println(firstNameSearch);
//					}
					textBox7.setText("First Name");
					
					String lastNameSearch = textBox07.getText();
//					if (!lastNameSearch.equals("Last Name")) {
//						System.out.println(lastNameSearch);
//					}
					textBox07.setText("Last Name");
					
					//Catch errors when strings are present in age
					int ageSearch;
					try {
						ageSearch = Integer.parseInt(textBox007.getText());
					} catch (NumberFormatException e) {
						ageSearch = 120;
					}
					
					if (ageSearch != 120 && !firstNameSearch.equals("First Name") &&
							!lastNameSearch.equals("Last Name")) {
						System.out.println(firstNameSearch);
						System.out.println(lastNameSearch);
						System.out.println(ageSearch);
						Person searchPerson = new Person(firstNameSearch,lastNameSearch,ageSearch);
						
						boolean personPresent = firstNameTree.search(searchPerson);
						
						if (personPresent) {
							System.out.println("Person Present");
						} else {
							System.out.println("Person Doesn't exist in this list");
						}
						
					}
					textBox007.setText("Age");
					
					
					System.out.println("------");
				}
			});
			
			
			
			
						
			Scene scene = new Scene(root,400,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Person Binary Search Tree");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
