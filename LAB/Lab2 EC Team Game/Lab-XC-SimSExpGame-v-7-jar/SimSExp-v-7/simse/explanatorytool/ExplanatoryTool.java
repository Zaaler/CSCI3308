/* File generated by: simse.codegenerator.explanatorytoolgenerator.ExplanatoryToolGenerator */
package simse.explanatorytool;

import simse.state.State;

import org.jfree.ui.RefineryUtilities;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ExplanatoryTool extends JFrame implements ActionListener, ListSelectionListener {
private ArrayList<State> log; // log for current simulation
private ArrayList<JFrame> visibleGraphs; // holds all of the currently visible graphs
private static MultipleTimelinesBrowser timelinesBrowser;
private JButton multipleTimelinesButton;
private JComboBox objectList; // for choosing an object whose attributes to graph
private JList attributeList; // for choosing which attributes to show
private JList actionList; // for choosing which actions to show
private JButton generateObjGraphButton; // for generating an object graph
private JButton generateActGraphButton; // for generating an action graph
private JButton generateCompGraphButton; // for generating a composite graph
private JComboBox actionComboBox;
private JList triggerRuleList;
private JList destroyerRuleList;
private JList intermediateRuleList;
private JTextArea descriptionArea;
private JButton closeButton;
private Box mainPane;
private Branch branch;

public ExplanatoryTool(JFrame owner, ArrayList<State> log, Branch branch, MultipleTimelinesBrowser browser) {
super();
this.branch = branch;
timelinesBrowser = browser;
String title = "Explanatory Tool";
if (branch.getName() != null) {
title = title.concat(" - " + branch.getName());
}
setTitle(title);
this.log = log;
this.visibleGraphs = new ArrayList<JFrame>();

// Create main panel (box):
mainPane = Box.createVerticalBox();

JPanel multipleTimelinesPanel = new JPanel();
multipleTimelinesButton = new JButton("Multiple Timelines Browser");
multipleTimelinesButton.addActionListener(this);
multipleTimelinesPanel.add(multipleTimelinesButton);

// Create main sub-panel:
JPanel generateGraphsPanel = new JPanel();

// Create generate graphs title pane and label:
JPanel generateGraphsTitlePane = new JPanel();
generateGraphsTitlePane.add(new JLabel("Generate Graph(s):"));

// Create object pane and components:
Box objectPane = Box.createVerticalBox();
JPanel objectTitlePane = new JPanel();
objectTitlePane.add(new JLabel("Object Graph:"));
objectPane.add(objectTitlePane);

// object list:
String[] objects = {"SoftwareDeveloper Employee Joyce",
"SoftwareDeveloper Employee Robert",
"SoftwareDeveloper Employee Timothy",
"SoftwareDeveloper Employee Reda",
"SoftwareDeveloper Employee Peg",
"SoftwareDeveloper Employee Sigfreido",
"Manager Employee Chang",
"CustomerRepresentative Customer Wayne",
"CustomerRep Employee Customer Wayne",
"UserStories Artifact Stories",
"ReleasePlan Artifact Release Plan",
"TheProject Project XP Project",
"CurrentIterationPlan Artifact IterationPlan",
"AcceptanceTests Artifact Test cases that customers and developers agree will be the criteria for acceptance of the software",
"CRCCards Tool Class Responsibility Collaborator Cards, a brainstorming tool for designing object-oriented software",
"Design Artifact CRC cards for this iteration's stories",
"UnitTestingFramework Tool Java-based unit testing framework",
"UnitTests Artifact Test cases for individual pieces of source code",
"Code Artifact The code for the current iteration's user stories",
"CodingStandard Tool A standard style and format for source code",
"RefactoringTool Tool Eclipse",
};
objectList = new JComboBox(objects);
objectList.addActionListener(this);
objectPane.add(objectList);

// Create attribute list pane:
JPanel attributeListTitlePane = new JPanel();
attributeListTitlePane.add(new JLabel("Show Attributes:"));
objectPane.add(attributeListTitlePane);
attributeList = new JList();
attributeList.setVisibleRowCount(5); // make 5 items visible at a time
attributeList.setFixedCellWidth(250);
attributeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
attributeList.addListSelectionListener(this);
JScrollPane attributeListPane = new JScrollPane(attributeList);
objectPane.add(attributeListPane);

// Create objectBottom pane & button:
JPanel objBottomPane = new JPanel();
generateObjGraphButton = new JButton("Generate Object Graph");
generateObjGraphButton.addActionListener(this);
objBottomPane.add(generateObjGraphButton);
objectPane.add(objBottomPane);

// Create action pane and components:
Box actionPane = Box.createVerticalBox();
JPanel actionTitlePane = new JPanel();
actionTitlePane.add(new JLabel("Action Graph:"));
actionPane.add(actionTitlePane);

// action list:
String[] actions = {
"CreateUserStories",
"ReleasePlanningMeeting",
"StartIteration",
"IterationPlanningMeeting",
"ChooseUserStoriesForIteration",
"CreateProgrammingTasks",
"CreateAcceptanceTests",
"Design",
"CreateUnitTests",
"LearnCodingStandard",
"Program",
"PairProgramRobertJoyce",
"PairProgramTimothyReda",
"PairProgramPegSigfreido",
"UnitTestingAndFixing",
"Refactor",
"IntegrateRobertJoyce",
"IntegrateTimothyReda",
"IntegratePegSigfreido",
"Integrate",
"AcceptanceTesting",
"ReleaseCodeAndEndIteration",
"RequireReleasePlanDoOver",
"CustomerComplains",
"AllEmployeesIdle",
"DeliverFinalProductToCustomer",
"GameOver",
};
actionList = new JList(actions);
actionList.setVisibleRowCount(5); // make 5 items visible at a time
actionList.setFixedCellWidth(250);
actionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
actionList.addListSelectionListener(this);
JScrollPane actionListPane = new JScrollPane(actionList);
actionPane.add(actionListPane);

// Create actionBottom pane & buttons:
JPanel actBottomPane = new JPanel();
generateActGraphButton = new JButton("Generate Action Graph");
generateActGraphButton.addActionListener(this);
actBottomPane.add(generateActGraphButton);
actionPane.add(actBottomPane);

// Create comp graph pane & button:
JPanel generateCompGraphPane = new JPanel();
generateCompGraphButton = new JButton("Generate Composite Graph");
generateCompGraphButton.addActionListener(this);
generateCompGraphPane.add(generateCompGraphButton);

refreshAttributeList();
if (actions.length > 0) {
actionList.setSelectedIndex(0);
}
refreshButtons();

// Create viewRuleTitlePane and label:
JPanel viewRulesTitlePane = new JPanel();
viewRulesTitlePane.add(new JLabel("View Rules:"));

// Create actionsComboBoxPane:
JPanel actionComboBoxPane = new JPanel();
actionComboBoxPane.add(new JLabel("Actions:"));
actionComboBox = new JComboBox(actions);
actionComboBox.addActionListener(this);
actionComboBoxPane.add(actionComboBox);

// Create rulesMainPane:
JPanel rulesMainPane = new JPanel();

// Create ruleListsPane:
Box ruleListsPane = Box.createVerticalBox();

// rule lists:
JPanel trigRuleTitlePane = new JPanel();
trigRuleTitlePane.add(new JLabel("Trigger Rules:"));
ruleListsPane.add(trigRuleTitlePane);
triggerRuleList = new JList();
triggerRuleList.setVisibleRowCount(4);
triggerRuleList.setFixedCellWidth(250);
triggerRuleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
triggerRuleList.addListSelectionListener(this);
JScrollPane triggerRuleListPane = new JScrollPane(triggerRuleList);
ruleListsPane.add(triggerRuleListPane);

JPanel destRuleTitlePane = new JPanel();
destRuleTitlePane.add(new JLabel("Destroyer Rules:"));
ruleListsPane.add(destRuleTitlePane);
destroyerRuleList = new JList();
destroyerRuleList.setVisibleRowCount(4);
destroyerRuleList.setFixedCellWidth(250);
destroyerRuleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
destroyerRuleList.addListSelectionListener(this);
JScrollPane destroyerRuleListPane = new JScrollPane(destroyerRuleList);
ruleListsPane.add(destroyerRuleListPane);

JPanel intRuleTitlePane = new JPanel();
intRuleTitlePane.add(new JLabel("Intermediate Rules:"));
ruleListsPane.add(intRuleTitlePane);
intermediateRuleList = new JList();
intermediateRuleList.setVisibleRowCount(4);
intermediateRuleList.setFixedCellWidth(250);
intermediateRuleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
intermediateRuleList.addListSelectionListener(this);
JScrollPane intermediateRuleListPane = new JScrollPane(intermediateRuleList);
ruleListsPane.add(intermediateRuleListPane);

rulesMainPane.add(ruleListsPane);

// description pane:
Box descriptionPane = Box.createVerticalBox();
JPanel descriptionTitlePane = new JPanel();
descriptionTitlePane.add(new JLabel("Description:"));
descriptionPane.add(descriptionTitlePane);

// description text area:
descriptionArea = new JTextArea(16, 30);
descriptionArea.setLineWrap(true);
descriptionArea.setWrapStyleWord(true);
descriptionArea.setEditable(false);
JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
descriptionPane.add(descriptionScrollPane);

rulesMainPane.add(descriptionPane);

// Create close button pane:
JPanel closeButtonPane = new JPanel();
closeButton = new JButton("Close");
closeButton.addActionListener(this);
closeButtonPane.add(closeButton);

if (actions.length > 0) { // at least one action in list
actionComboBox.setSelectedIndex(0);
}

// set up tool tips:
setUpToolTips();

// Add panes to main pane and main sub-pane:
mainPane.add(multipleTimelinesPanel);
JSeparator separator0 = new JSeparator();
separator0.setMaximumSize(new Dimension(2900, 1));
mainPane.add(separator0);
generateGraphsPanel.add(objectPane);
generateGraphsPanel.add(actionPane);
mainPane.add(generateGraphsTitlePane);
JSeparator separator1 = new JSeparator();
separator1.setMaximumSize(new Dimension(2900, 1));
mainPane.add(separator1);
mainPane.add(generateGraphsPanel);
JSeparator separator2 = new JSeparator();
separator2.setMaximumSize(new Dimension(2900, 1));
mainPane.add(separator2);
mainPane.add(generateCompGraphPane);
JSeparator separator3 = new JSeparator();
separator3.setMaximumSize(new Dimension(2900, 1));
mainPane.add(separator3);
mainPane.add(viewRulesTitlePane);
JSeparator separator4 = new JSeparator();
separator4.setMaximumSize(new Dimension(2900, 1));
mainPane.add(separator4);
mainPane.add(actionComboBoxPane);
mainPane.add(rulesMainPane);
JSeparator separator5 = new JSeparator();
separator5.setMaximumSize(new Dimension(2900, 1));
mainPane.add(separator5);
mainPane.add(closeButtonPane);

// Set main window frame properties:
setBackground(Color.black);
setContentPane(mainPane);
validate();
pack();
repaint();
toFront();
// Make it show up in the center of the screen:
RefineryUtilities.centerFrameOnScreen(this);
setVisible(false);
}

public void actionPerformed(ActionEvent evt) {
Object source = evt.getSource(); // get which component the action came from
if (source == objectList) { // user has chosen an object
refreshAttributeList();
}
else if (source == multipleTimelinesButton) {
if (timelinesBrowser.getState() == Frame.ICONIFIED) {
timelinesBrowser.setState(Frame.NORMAL);
}
timelinesBrowser.setVisible(true);
}
else if (source == generateObjGraphButton) { // generateObjGraphButton has been pressed
String selectedObj = (String)objectList.getSelectedItem();
String[] words = selectedObj.split("\\s");
String title = selectedObj + " Attributes";
String objType = words[0];
String objTypeType = words[1];

// add 2 for the 2 spaces:
String keyAttVal = selectedObj.substring(objType.length() + objTypeType.length() + 2);

Object[] selectedAtts = attributeList.getSelectedValues();
String[] attributes = new String[selectedAtts.length];
for (int i = 0; i < selectedAtts.length; i++) {
attributes[i] = new String((String)selectedAtts[i]);
}
if (attributes.length > 0) { // at least one attribute is selected
ObjectGraph graph = new ObjectGraph(title, log, objTypeType, objType, keyAttVal, attributes, true, branch);
visibleGraphs.add(graph);
}
else {
JOptionPane.showMessageDialog(null, ("Please select at least one attribute"), "Warning", JOptionPane.WARNING_MESSAGE);
}
}
else if (source == generateActGraphButton) { // generateActGraphButton has been pressed
Object[] selectedActions = actionList.getSelectedValues();
String[] actions = new String[selectedActions.length];
for (int i = 0; i < selectedActions.length; i++) {
actions[i] = new String((String) selectedActions[i]);
}
if (actions.length > 0) { // at least one attribute is selected
ActionGraph graph = new ActionGraph(log, actions, true, branch);
visibleGraphs.add(graph);
}
else {
JOptionPane.showMessageDialog(null, ("Please select at least one action"), "Warning", JOptionPane.WARNING_MESSAGE);
}
}
else if (source == generateCompGraphButton) { // generateCompGraphButton has been pressed
String selectedObj = (String) objectList.getSelectedItem();
String[] words = selectedObj.split("\\s");
String title = selectedObj + " Attributes";
String objType = words[0];
String objTypeType = words[1];

// add 2 for the 2 spaces:
String keyAttVal = selectedObj.substring(objType.length() + objTypeType.length() + 2);

Object[] selectedAtts = attributeList.getSelectedValues();
String[] attributes = new String[selectedAtts.length];
for (int i = 0; i < selectedAtts.length; i++) {
attributes[i] = new String((String) selectedAtts[i]);
}
if (attributes.length > 0) { // at least one attribute is selected
ObjectGraph objGraph = new ObjectGraph(title, log, objTypeType, objType, keyAttVal, attributes, false, branch);

Object[] selectedActions = actionList.getSelectedValues();
String[] actions = new String[selectedActions.length];
for (int i = 0; i < selectedActions.length; i++) {
actions[i] = new String((String) selectedActions[i]);
}
if (actions.length > 0) { // at least one attribute is selected
ActionGraph actGraph = new ActionGraph(log, actions, false, branch);

// generate composite graph:
CompositeGraph compGraph = new CompositeGraph(objGraph, actGraph, branch);
visibleGraphs.add(compGraph);
}
else {
JOptionPane.showMessageDialog(null, ("Please select at least one action"), "Warning", JOptionPane.WARNING_MESSAGE);
}
}
else {
JOptionPane.showMessageDialog(null, ("Please select at least one attribute"), "Warning", JOptionPane.WARNING_MESSAGE);
}
}
else if (source == actionComboBox) {
if (actionComboBox.getItemCount() > 0) {
refreshRuleLists((String)actionComboBox.getSelectedItem());
descriptionArea.setText("");
}
}
else if (source == closeButton) {
setVisible(false);
dispose();
}
}

public void valueChanged(ListSelectionEvent e) {
if ((e.getSource() == attributeList) || (e.getSource() == actionList)) {
refreshButtons();
}
else if ((e.getSource() == triggerRuleList && !triggerRuleList.isSelectionEmpty())) {
destroyerRuleList.clearSelection();
intermediateRuleList.clearSelection();
refreshDescriptionArea((String)triggerRuleList.getSelectedValue());
}
else if (e.getSource() == destroyerRuleList && !destroyerRuleList.isSelectionEmpty()) {
triggerRuleList.clearSelection();
intermediateRuleList.clearSelection();
refreshDescriptionArea((String)destroyerRuleList.getSelectedValue());
}
else if (e.getSource() == intermediateRuleList && !intermediateRuleList.isSelectionEmpty()) {
triggerRuleList.clearSelection();
destroyerRuleList.clearSelection();
refreshDescriptionArea((String)intermediateRuleList.getSelectedValue());
}
}

// Updates all of the visible graphs
public void update() {
for (int i = 0; i < visibleGraphs.size(); i++) {
JFrame graph = visibleGraphs.get(i);
// remove graphs whose windows have been closed from visibleGraphs:
if (!graph.isShowing()) {
visibleGraphs.remove(graph);
}
else if (graph instanceof ObjectGraph) {
((ObjectGraph)graph).update();
}
else if (graph instanceof ActionGraph) {
((ActionGraph)graph).update();
}
else if (graph instanceof CompositeGraph) {
((CompositeGraph)graph).update();
}
}

// update timelines browser:
if (timelinesBrowser != null) {
timelinesBrowser.update();
}
}

private void refreshAttributeList() {
attributeList.removeAll();
String selectedObject = (String)objectList.getSelectedItem();
if (selectedObject.startsWith("SoftwareDeveloper Employee")) {
String[] attributes = {
"(No numerical attributes)"
};
attributeList.setListData(attributes);
attributeList.setEnabled(false);
}
else if (selectedObject.startsWith("Manager Employee")) {
String[] attributes = {
"(No numerical attributes)"
};
attributeList.setListData(attributes);
attributeList.setEnabled(false);
}
else if (selectedObject.startsWith("CustomerRepresentative Customer")) {
String[] attributes = {
"(No numerical attributes)"
};
attributeList.setListData(attributes);
attributeList.setEnabled(false);
}
else if (selectedObject.startsWith("UserStories Artifact")) {
String[] attributes = {
"NumUserStoriesSpecified",
"NumUserStoriesImplemented",
"SpecificationCompleteness",
"ImplementationCompleteness",
"NumUserStoriesIntegrated",
"PercentErroneous",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("CustomerRep Employee")) {
String[] attributes = {
"(No numerical attributes)"
};
attributeList.setListData(attributes);
attributeList.setEnabled(false);
}
else if (selectedObject.startsWith("ReleasePlan Artifact")) {
String[] attributes = {
"Completeness",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("TheProject Project")) {
String[] attributes = {
"CurrentIteration",
"Score",
"TimeElapsed",
"TimeAllotted",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("CurrentIterationPlan Artifact")) {
String[] attributes = {
"Completeness",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("AcceptanceTests Artifact")) {
String[] attributes = {
"Completeness",
"TestsRun",
"TestsFailed",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("CRCCards Tool")) {
String[] attributes = {
"(No numerical attributes)"
};
attributeList.setListData(attributes);
attributeList.setEnabled(false);
}
else if (selectedObject.startsWith("Design Artifact")) {
String[] attributes = {
"NumCRCCardsCompleted",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("UnitTestingFramework Tool")) {
String[] attributes = {
"ProductivityIncreaseFactor",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("UnitTests Artifact")) {
String[] attributes = {
"Completeness",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("Code Artifact")) {
String[] attributes = {
"PercentErroneous",
"Completeness",
"PercentRefactored",
"PercentIntegrated",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("CodingStandard Tool")) {
String[] attributes = {
"ProductivityIncreaseFactor",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
else if (selectedObject.startsWith("RefactoringTool Tool")) {
String[] attributes = {
"ProductivityIncreaseFactor",
};
attributeList.setListData(attributes);
attributeList.setEnabled(true);
attributeList.setSelectedIndex(0);
}
}

private void setUpToolTips() {
objectList.setToolTipText("Choose an object to graph");
attributeList.setToolTipText("Choose which attributes to graph");
actionList.setToolTipText("Choose which actions to graph");
actionComboBox.setToolTipText("Choose which action to show rules for");
triggerRuleList.setToolTipText("Rules that execute at the beginning of the action");
destroyerRuleList.setToolTipText("Rules that execute at the end of the action");
intermediateRuleList.setToolTipText("Rules that execute every clock tick during the life of the action");
}
private void refreshButtons() {
if (attributeList.isSelectionEmpty()) { // no attributes selected
generateObjGraphButton.setEnabled(false);
generateCompGraphButton.setEnabled(false);
}
else { // an attribute is selected
generateObjGraphButton.setEnabled(true);
if (!actionList.isSelectionEmpty()) { // an action is also selected
generateCompGraphButton.setEnabled(true);
}
}
if (actionList.isSelectionEmpty()) { // no actions selected
generateActGraphButton.setEnabled(false);
generateCompGraphButton.setEnabled(false);
}
else { // an action is selected
generateActGraphButton.setEnabled(true);
}
}

private void refreshRuleLists(String actionName) {
triggerRuleList.setListData(new Vector());
destroyerRuleList.setListData(new Vector());
intermediateRuleList.setListData(new Vector());

if (actionName.equals("CreateUserStories")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncNumUserStories",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("ReleasePlanningMeeting")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
"SetUserStoriesPrioritized",
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncReleasePlanCompleteness",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("StartIteration")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
}
else if (actionName.equals("IterationPlanningMeeting")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("ChooseUserStoriesForIteration")) {
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncIterationPlanCompletenessUserStories",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("CreateProgrammingTasks")) {
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncIterationPlanCompletnessProTasks",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("CreateAcceptanceTests")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncCompletenessAccTests",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("Design")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncDesignCompleteness",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("CreateUnitTests")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncCompletenessUnitTests",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("LearnCodingStandard")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncreaseCodingStandardKnowledge",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("Program")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncCodeCompletenessProgram",
"SetCodePercentErroneousProgram",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("PairProgramRobertJoyce")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncCodeCompletenessPairProgramRJ",
"SetCodePercentErroneousPairProgramRJ",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("PairProgramTimothyReda")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncCodeCompletenessPairProgramTR",
"SetCodePercentErroneousPairProgramTR",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("PairProgramPegSigfreido")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"SetCodePercentErroneousPairProgramPS",
"IncCodeCompletenessPairProgramPS",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("UnitTestingAndFixing")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"RemoveErrorsFromCode",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("Refactor")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncPercentRefactored",
"IncNumErrorsRefactor",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("IntegrateRobertJoyce")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncPercentIntegratedCodeRJ",
"IncNumErrorsIntegrateRJ",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("IntegrateTimothyReda")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncPercentIntegratedCodeTR",
"IncNumErrorsIntegrateTR",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("IntegratePegSigfreido")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncPercentIntegratedCodePS",
"IncNumErrorsIntegratePS",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("Integrate")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncPercentIntegratedCode",
"IncNumErrorsIntegrate",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("AcceptanceTesting")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
"SetNumFailedTests",
"SetCodePcntErroneousAccTesting",
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncAcceptanceTestRun",
};
intermediateRuleList.setListData(intList);
}
else if (actionName.equals("ReleaseCodeAndEndIteration")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
}
else if (actionName.equals("RequireReleasePlanDoOver")) {
String[] trigList = {
"ResetReleasePlanCompleteness",
"DeactivateOtherActions",
};
triggerRuleList.setListData(trigList);
String[] destList = {
"ReactivateOtherActions",
};
destroyerRuleList.setListData(destList);
}
else if (actionName.equals("CustomerComplains")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
}
else if (actionName.equals("AllEmployeesIdle")) {
}
else if (actionName.equals("DeliverFinalProductToCustomer")) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
}
else if (actionName.equals("GameOver")) {
String[] trigList = {
"CalculateScore",
};
triggerRuleList.setListData(trigList);
}
}
// refreshes the description area with the selected rule description
private void refreshDescriptionArea(String ruleName) {
if (ruleName != null) {
String text = "";
if (ruleName.equals("IncNumUserStories")) {
text = RuleDescriptions.CREATEUSERSTORIES_INCNUMUSERSTORIES;
}else if (ruleName.equals("IncReleasePlanCompleteness")) {
text = RuleDescriptions.RELEASEPLANNINGMEETING_INCRELEASEPLANCOMPLETENESS;
}else if (ruleName.equals("SetUserStoriesPrioritized")) {
text = RuleDescriptions.RELEASEPLANNINGMEETING_SETUSERSTORIESPRIORITIZED;
}else if (ruleName.equals("IncIterationPlanCompletenessUserStories")) {
text = RuleDescriptions.CHOOSEUSERSTORIESFORITERATION_INCITERATIONPLANCOMPLETENESSUSERSTORIES;
}else if (ruleName.equals("IncIterationPlanCompletnessProTasks")) {
text = RuleDescriptions.CREATEPROGRAMMINGTASKS_INCITERATIONPLANCOMPLETNESSPROTASKS;
}else if (ruleName.equals("IncCompletenessAccTests")) {
text = RuleDescriptions.CREATEACCEPTANCETESTS_INCCOMPLETENESSACCTESTS;
}else if (ruleName.equals("IncDesignCompleteness")) {
text = RuleDescriptions.DESIGN_INCDESIGNCOMPLETENESS;
}else if (ruleName.equals("IncCompletenessUnitTests")) {
text = RuleDescriptions.CREATEUNITTESTS_INCCOMPLETENESSUNITTESTS;
}else if (ruleName.equals("IncreaseCodingStandardKnowledge")) {
text = RuleDescriptions.LEARNCODINGSTANDARD_INCREASECODINGSTANDARDKNOWLEDGE;
}else if (ruleName.equals("IncCodeCompletenessProgram")) {
text = RuleDescriptions.PROGRAM_INCCODECOMPLETENESSPROGRAM;
}else if (ruleName.equals("SetCodePercentErroneousProgram")) {
text = RuleDescriptions.PROGRAM_SETCODEPERCENTERRONEOUSPROGRAM;
}else if (ruleName.equals("IncCodeCompletenessPairProgramRJ")) {
text = RuleDescriptions.PAIRPROGRAMROBERTJOYCE_INCCODECOMPLETENESSPAIRPROGRAMRJ;
}else if (ruleName.equals("SetCodePercentErroneousPairProgramRJ")) {
text = RuleDescriptions.PAIRPROGRAMROBERTJOYCE_SETCODEPERCENTERRONEOUSPAIRPROGRAMRJ;
}else if (ruleName.equals("IncCodeCompletenessPairProgramTR")) {
text = RuleDescriptions.PAIRPROGRAMTIMOTHYREDA_INCCODECOMPLETENESSPAIRPROGRAMTR;
}else if (ruleName.equals("SetCodePercentErroneousPairProgramTR")) {
text = RuleDescriptions.PAIRPROGRAMTIMOTHYREDA_SETCODEPERCENTERRONEOUSPAIRPROGRAMTR;
}else if (ruleName.equals("IncCodeCompletenessPairProgramPS")) {
text = RuleDescriptions.PAIRPROGRAMPEGSIGFREIDO_INCCODECOMPLETENESSPAIRPROGRAMPS;
}else if (ruleName.equals("SetCodePercentErroneousPairProgramPS")) {
text = RuleDescriptions.PAIRPROGRAMPEGSIGFREIDO_SETCODEPERCENTERRONEOUSPAIRPROGRAMPS;
}else if (ruleName.equals("RemoveErrorsFromCode")) {
text = RuleDescriptions.UNITTESTINGANDFIXING_REMOVEERRORSFROMCODE;
}else if (ruleName.equals("IncPercentRefactored")) {
text = RuleDescriptions.REFACTOR_INCPERCENTREFACTORED;
}else if (ruleName.equals("IncNumErrorsRefactor")) {
text = RuleDescriptions.REFACTOR_INCNUMERRORSREFACTOR;
}else if (ruleName.equals("IncPercentIntegratedCodeRJ")) {
text = RuleDescriptions.INTEGRATEROBERTJOYCE_INCPERCENTINTEGRATEDCODERJ;
}else if (ruleName.equals("IncNumErrorsIntegrateRJ")) {
text = RuleDescriptions.INTEGRATEROBERTJOYCE_INCNUMERRORSINTEGRATERJ;
}else if (ruleName.equals("IncPercentIntegratedCodeTR")) {
text = RuleDescriptions.INTEGRATETIMOTHYREDA_INCPERCENTINTEGRATEDCODETR;
}else if (ruleName.equals("IncNumErrorsIntegrateTR")) {
text = RuleDescriptions.INTEGRATETIMOTHYREDA_INCNUMERRORSINTEGRATETR;
}else if (ruleName.equals("IncPercentIntegratedCodePS")) {
text = RuleDescriptions.INTEGRATEPEGSIGFREIDO_INCPERCENTINTEGRATEDCODEPS;
}else if (ruleName.equals("IncNumErrorsIntegratePS")) {
text = RuleDescriptions.INTEGRATEPEGSIGFREIDO_INCNUMERRORSINTEGRATEPS;
}else if (ruleName.equals("IncPercentIntegratedCode")) {
text = RuleDescriptions.INTEGRATE_INCPERCENTINTEGRATEDCODE;
}else if (ruleName.equals("IncNumErrorsIntegrate")) {
text = RuleDescriptions.INTEGRATE_INCNUMERRORSINTEGRATE;
}else if (ruleName.equals("IncAcceptanceTestRun")) {
text = RuleDescriptions.ACCEPTANCETESTING_INCACCEPTANCETESTRUN;
}else if (ruleName.equals("SetNumFailedTests")) {
text = RuleDescriptions.ACCEPTANCETESTING_SETNUMFAILEDTESTS;
}else if (ruleName.equals("SetCodePcntErroneousAccTesting")) {
text = RuleDescriptions.ACCEPTANCETESTING_SETCODEPCNTERRONEOUSACCTESTING;
}else if (ruleName.equals("ResetReleasePlanCompleteness")) {
text = RuleDescriptions.REQUIRERELEASEPLANDOOVER_RESETRELEASEPLANCOMPLETENESS;
}else if (ruleName.equals("DeactivateOtherActions")) {
text = RuleDescriptions.REQUIRERELEASEPLANDOOVER_DEACTIVATEOTHERACTIONS;
}else if (ruleName.equals("ReactivateOtherActions")) {
text = RuleDescriptions.REQUIRERELEASEPLANDOOVER_REACTIVATEOTHERACTIONS;
}else if (ruleName.equals("CalculateScore")) {
text = RuleDescriptions.GAMEOVER_CALCULATESCORE;
}descriptionArea.setText(text);
descriptionArea.setCaretPosition(0);
}
}
}