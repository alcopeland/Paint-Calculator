import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Creates the main display which can take user input in text boxes and allows the user to select the unit and desired result
 */
public class MainDisplay implements ActionListener
{
    private double length=0; //creates a variable to store the room length
    private double width=0; //creates a variable to store the room width
    private double height=0; //creates a variable to store the room height
    private double result=0; //creates a variable to store the result

    private String word; //creates a string to store the inital text in the answer box
    private String units; //creates a string to store the units
    private String dimension; //creates a string to store the dimension of the result
    private boolean test = false; //creates a boolean to determine whether the input is valid

    private JFrame frame = new JFrame(); //creates the main frame
    private JPanel mainPanel = new JPanel(); //creates the main panel
    private JPanel centrePanel = new JPanel(); //creates the panel to store the text boxes and some labels and buttons
    private JPanel bottomPanel = new JPanel(); //creates a panel to go at the bottom storing the units, results and compute button
    private JPanel unitsBar = new JPanel(); //creates a panel to store the units buttons

    private BorderLayout border = new BorderLayout(); //creates the various layouts used to properly structure my window
    private GridLayout grid = new GridLayout(3,3);
    private GridLayout grid2 = new GridLayout(3,1);
    private GridLayout grid3 = new GridLayout(1,5);

    private JButton computeButton = new JButton("Compute"); //creates the button to compute the result
    private JLabel instructions = new JLabel("Please enter your values below:"); //creates the labels to display text
    private JLabel results = new JLabel("Result will show here."); 

    private JLabel lengthLabel = new JLabel("Length"); 
    private JLabel widthLabel = new JLabel("Width");
    private JLabel heightLabel = new JLabel("Height");

    private JTextField lengthInput = new JTextField(""+length); //creates the text fields to allow user input
    private JTextField widthInput = new JTextField(""+width);
    private JTextField heightInput = new JTextField(""+height);

    private JRadioButton floorButton = new JRadioButton("Floor Area"); //creates the set of radio buttons to select the result
    private JRadioButton wallButton = new JRadioButton("Wall Area");
    private JRadioButton roomButton = new JRadioButton("Room Volume");
    private ButtonGroup resultGroup = new ButtonGroup();

    private JRadioButton milesButton = new JRadioButton("Miles"); //creates the set of radio buttons to select the units
    private JRadioButton kiloButton = new JRadioButton("Kilometres");
    private JRadioButton metreButton = new JRadioButton("Metres");
    private JRadioButton feetButton = new JRadioButton("Feet");
    private JRadioButton cmButton = new JRadioButton("Centimetres");
    private ButtonGroup unitsGroup = new ButtonGroup();

    /**
     * This is the constructor that creates an instance of the base display
     */
    public MainDisplay()
    {
        mainPanel.setLayout(border); //sets the panels to the required layout
        centrePanel.setLayout(grid);
        bottomPanel.setLayout(grid2);
        unitsBar.setLayout(grid3);

        unitsGroup.add(milesButton); //adds the unit buttons to a button group so only one can be selected at a time
        unitsGroup.add(kiloButton);
        unitsGroup.add(metreButton);
        unitsGroup.add(feetButton);
        unitsGroup.add(cmButton);
        unitsBar.add(milesButton); //adds the unit buttons to a panel
        unitsBar.add(kiloButton);
        unitsBar.add(metreButton);
        unitsBar.add(feetButton);
        unitsBar.add(cmButton);

        centrePanel.add(lengthLabel); //adds the labels to a panel
        centrePanel.add(widthLabel);
        centrePanel.add(heightLabel);

        centrePanel.add(lengthInput); //adds the text fields to a panel
        centrePanel.add(widthInput);
        centrePanel.add(heightInput);
        
        resultGroup.add(floorButton); //adds the result buttons to a button group so only one can be pressed at a time
        resultGroup.add(wallButton);
        resultGroup.add(roomButton);
        centrePanel.add(floorButton); //adds the result buttons to a panel
        centrePanel.add(wallButton);
        centrePanel.add(roomButton);

        bottomPanel.add(unitsBar); //adds the unit panel to a higher panel
        computeButton.addActionListener(this); //adds an action listener to the button to listen for when it is pressed and compute the result
        bottomPanel.add(computeButton);// adds the button the a panel
        bottomPanel.add(results); //adds the results label to a panel
        
        mainPanel.add(instructions,BorderLayout.NORTH); //adds the 3 sub panels to the main panel in the correct position
        mainPanel.add(centrePanel,BorderLayout.CENTER);
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the action to perform when the cross is pressed
        frame.setTitle("Paint Calculator"); //names the frame
        frame.setLocation(100,200); //sets the location the frame opens
        frame.setSize(600,250); //sets the size of the frame
        frame.setContentPane(mainPanel); //sets the content panel of the frame
        frame.setVisible(true); //sets the frame to be visible
    }

    /**
     * Listens for the press of the compute button and then gets the required data and computes the result and displays it
     * Checks to see if both a unit and result is selected, if not says invalid input
     * Checks for a valid input in the text field, if not says invalid input
     * @param e takes action event e on buuuuutton press
     */
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            length = Double.parseDouble(lengthInput.getText()); //sets length to the value in the corresponding text field, but expects an exception if the value entered is not a number
            width = Double.parseDouble(widthInput.getText()); //sets width to the value in the corresponding text field, but expects an exception if the value entered is not a number
            height = Double.parseDouble(heightInput.getText()); //sets height to the value in the corresponding text field, but expects an exception if the value entered is not a number
        }
        catch(NumberFormatException exp)
        {
            test=true; //if an exception occurs test is set to true so the maths code is skipped and no error occurs
            
        }

        if(test==false) //if the input is valid, set the text in the results label to the correct title and dimension
        {
            if(floorButton.isSelected()==true)
            {
                result=length*width;
                word="Floor Area";
                dimension="Square";
            }
            else if(wallButton.isSelected()==true)
            {
                result=((2*length*height)+(2*width*height));
                word="Wall Area";
                dimension="Square";
            }
            else if(roomButton.isSelected()==true)
            {
                result=length*width*height;
                word="Room Volume";
                dimension="Cubic";
            }
            else //if no result is selected say invalid input
            {
                test=true;
            }
        }
        if(test==false) //if a valid result button is pressed, set the unit variable to the correct unit
        {
            if(milesButton.isSelected()==true)
            {
                units="Miles";
            }
            else if(kiloButton.isSelected()==true)
            {
                units="Kilometres";
            }
            else if(metreButton.isSelected()==true)
            {
                units="Metres";
            }
            else if(feetButton.isSelected()==true)
            {
                units="Feet";
            }
            else if(cmButton.isSelected()==true)
            {
                units="Centimetres";
            }
            else //if no result is selected say invalid input
            {
                test=true;
            }
        }
        if(test==true)
        {
            results.setText("Invalid Input");
        }
        else if(test==false) //if all tests passed, says the final result combined
        {
            results.setText(word+": "+result+" "+dimension+" "+units);
        }
        test=false; //resets the test variable for the next button press
    }
}