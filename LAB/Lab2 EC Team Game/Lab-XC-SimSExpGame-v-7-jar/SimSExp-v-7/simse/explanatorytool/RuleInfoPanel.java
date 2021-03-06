/* File generated by: simse.codegenerator.explanatorytoolgenerator.RuleInfoPanelGenerator */
package simse.explanatorytool;

import simse.adts.actions.*;
import simse.explanatorytool.RuleDescriptions;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;

public class RuleInfoPanel extends JPanel implements ListSelectionListener {
private simse.adts.actions.Action action; // action in focus

private JList triggerRuleList;
private JList destroyerRuleList;
private JList intermediateRuleList;
private JTextArea descriptionArea; // for displaying a rule description

public RuleInfoPanel(JFrame owner, simse.adts.actions.Action action) {
this.action = action;

// Create main panel:
JPanel mainPane = new JPanel();
mainPane.setPreferredSize(new Dimension(900, 550));

// Create rule pane and components:
Box rulePane = Box.createVerticalBox();
JPanel trigRuleTitlePane = new JPanel();
trigRuleTitlePane.add(new JLabel("Trigger Rules:"));
rulePane.add(trigRuleTitlePane);

// rule lists:
triggerRuleList = new JList();
triggerRuleList.setVisibleRowCount(7);
triggerRuleList.setFixedCellWidth(400);
triggerRuleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
triggerRuleList.addListSelectionListener(this);
JScrollPane triggerRuleListPane = new JScrollPane(triggerRuleList);
String trigToolTip = "Rules that execute at the beginning of the action";
trigRuleTitlePane.setToolTipText(trigToolTip);
triggerRuleList.setToolTipText(trigToolTip);
rulePane.add(triggerRuleListPane);

JPanel destRuleTitlePane = new JPanel();
destRuleTitlePane.add(new JLabel("Destroyer Rules:"));
rulePane.add(destRuleTitlePane);
destroyerRuleList = new JList();
destroyerRuleList.setVisibleRowCount(7);
destroyerRuleList.setFixedCellWidth(400);
destroyerRuleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
destroyerRuleList.addListSelectionListener(this);
JScrollPane destroyerRuleListPane = new JScrollPane(destroyerRuleList);
String destToolTip = "Rules that execute at the end of the action";
destRuleTitlePane.setToolTipText(destToolTip);
destroyerRuleList.setToolTipText(destToolTip);
rulePane.add(destroyerRuleListPane);

JPanel intRuleTitlePane = new JPanel();
intRuleTitlePane.add(new JLabel("Intermediate Rules:"));
rulePane.add(intRuleTitlePane);
intermediateRuleList = new JList();
intermediateRuleList.setVisibleRowCount(7);
intermediateRuleList.setFixedCellWidth(400);
intermediateRuleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
intermediateRuleList.addListSelectionListener(this);
JScrollPane intermediateRuleListPane = new JScrollPane(intermediateRuleList);
String intToolTip = "Rules that execute every clock tick during the life of the action";
intRuleTitlePane.setToolTipText(intToolTip);
intermediateRuleList.setToolTipText(intToolTip);
rulePane.add(intermediateRuleListPane);

initializeRuleLists();

// description pane:
Box descriptionPane = Box.createVerticalBox();
JPanel descriptionTitlePane = new JPanel();
descriptionTitlePane.add(new JLabel("Description:"));
descriptionPane.add(descriptionTitlePane);

// description text area:
descriptionArea = new JTextArea(29, 30);
descriptionArea.setLineWrap(true);
descriptionArea.setWrapStyleWord(true);
descriptionArea.setEditable(false);
JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
descriptionPane.add(descriptionScrollPane);

rulePane.add(descriptionPane);

// Add panes to main pane:
mainPane.add(rulePane);
mainPane.add(descriptionPane);
add(mainPane);

setOpaque(true);
validate();
repaint();
}

public void valueChanged(ListSelectionEvent e) {
if ((e.getSource() == triggerRuleList && !triggerRuleList.isSelectionEmpty())) {
destroyerRuleList.clearSelection();
intermediateRuleList.clearSelection();
refreshDescriptionArea();
}
else if (e.getSource() == destroyerRuleList && !destroyerRuleList.isSelectionEmpty()) {
triggerRuleList.clearSelection();
intermediateRuleList.clearSelection();
refreshDescriptionArea();
}
else if (e.getSource() == intermediateRuleList && !intermediateRuleList.isSelectionEmpty()) {
triggerRuleList.clearSelection();
destroyerRuleList.clearSelection();
refreshDescriptionArea();
}
}

private void initializeRuleLists() {
if (action instanceof CreateUserStoriesAction) {
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
else if (action instanceof ReleasePlanningMeetingAction) {
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
else if (action instanceof StartIterationAction) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
}
else if (action instanceof IterationPlanningMeetingAction) {
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
else if (action instanceof ChooseUserStoriesForIterationAction) {
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncIterationPlanCompletenessUserStories",
};
intermediateRuleList.setListData(intList);
}
else if (action instanceof CreateProgrammingTasksAction) {
String[] destList = {
};
destroyerRuleList.setListData(destList);
String[] intList = {
"IncIterationPlanCompletnessProTasks",
};
intermediateRuleList.setListData(intList);
}
else if (action instanceof CreateAcceptanceTestsAction) {
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
else if (action instanceof DesignAction) {
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
else if (action instanceof CreateUnitTestsAction) {
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
else if (action instanceof LearnCodingStandardAction) {
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
else if (action instanceof ProgramAction) {
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
else if (action instanceof PairProgramRobertJoyceAction) {
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
else if (action instanceof PairProgramTimothyRedaAction) {
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
else if (action instanceof PairProgramPegSigfreidoAction) {
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
else if (action instanceof UnitTestingAndFixingAction) {
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
else if (action instanceof RefactorAction) {
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
else if (action instanceof IntegrateRobertJoyceAction) {
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
else if (action instanceof IntegrateTimothyRedaAction) {
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
else if (action instanceof IntegratePegSigfreidoAction) {
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
else if (action instanceof IntegrateAction) {
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
else if (action instanceof AcceptanceTestingAction) {
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
else if (action instanceof ReleaseCodeAndEndIterationAction) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
}
else if (action instanceof RequireReleasePlanDoOverAction) {
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
else if (action instanceof CustomerComplainsAction) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
String[] destList = {
};
destroyerRuleList.setListData(destList);
}
else if (action instanceof AllEmployeesIdleAction) {
}
else if (action instanceof DeliverFinalProductToCustomerAction) {
String[] trigList = {
};
triggerRuleList.setListData(trigList);
}
else if (action instanceof GameOverAction) {
String[] trigList = {
"CalculateScore",
};
triggerRuleList.setListData(trigList);
}
}

// refreshes the description area with the selected rule description
private void refreshDescriptionArea() {
String name = null;
if (!triggerRuleList.isSelectionEmpty()) {
name = (String) triggerRuleList.getSelectedValue();
}
else if (!destroyerRuleList.isSelectionEmpty()) {
name = (String) destroyerRuleList.getSelectedValue();
}
else if (!intermediateRuleList.isSelectionEmpty()) {
name = (String) intermediateRuleList.getSelectedValue();
}
if (name != null) {
String text = "";
if (action instanceof CreateUserStoriesAction) {
if (name.equals("IncNumUserStories")) {
text = RuleDescriptions.CREATEUSERSTORIES_INCNUMUSERSTORIES;
}}
else if (action instanceof ReleasePlanningMeetingAction) {
if (name.equals("IncReleasePlanCompleteness")) {
text = RuleDescriptions.RELEASEPLANNINGMEETING_INCRELEASEPLANCOMPLETENESS;
}else if (name.equals("SetUserStoriesPrioritized")) {
text = RuleDescriptions.RELEASEPLANNINGMEETING_SETUSERSTORIESPRIORITIZED;
}}
else if (action instanceof StartIterationAction) {
}
else if (action instanceof IterationPlanningMeetingAction) {
}
else if (action instanceof ChooseUserStoriesForIterationAction) {
if (name.equals("IncIterationPlanCompletenessUserStories")) {
text = RuleDescriptions.CHOOSEUSERSTORIESFORITERATION_INCITERATIONPLANCOMPLETENESSUSERSTORIES;
}}
else if (action instanceof CreateProgrammingTasksAction) {
if (name.equals("IncIterationPlanCompletnessProTasks")) {
text = RuleDescriptions.CREATEPROGRAMMINGTASKS_INCITERATIONPLANCOMPLETNESSPROTASKS;
}}
else if (action instanceof CreateAcceptanceTestsAction) {
if (name.equals("IncCompletenessAccTests")) {
text = RuleDescriptions.CREATEACCEPTANCETESTS_INCCOMPLETENESSACCTESTS;
}}
else if (action instanceof DesignAction) {
if (name.equals("IncDesignCompleteness")) {
text = RuleDescriptions.DESIGN_INCDESIGNCOMPLETENESS;
}}
else if (action instanceof CreateUnitTestsAction) {
if (name.equals("IncCompletenessUnitTests")) {
text = RuleDescriptions.CREATEUNITTESTS_INCCOMPLETENESSUNITTESTS;
}}
else if (action instanceof LearnCodingStandardAction) {
if (name.equals("IncreaseCodingStandardKnowledge")) {
text = RuleDescriptions.LEARNCODINGSTANDARD_INCREASECODINGSTANDARDKNOWLEDGE;
}}
else if (action instanceof ProgramAction) {
if (name.equals("IncCodeCompletenessProgram")) {
text = RuleDescriptions.PROGRAM_INCCODECOMPLETENESSPROGRAM;
}else if (name.equals("SetCodePercentErroneousProgram")) {
text = RuleDescriptions.PROGRAM_SETCODEPERCENTERRONEOUSPROGRAM;
}}
else if (action instanceof PairProgramRobertJoyceAction) {
if (name.equals("IncCodeCompletenessPairProgramRJ")) {
text = RuleDescriptions.PAIRPROGRAMROBERTJOYCE_INCCODECOMPLETENESSPAIRPROGRAMRJ;
}else if (name.equals("SetCodePercentErroneousPairProgramRJ")) {
text = RuleDescriptions.PAIRPROGRAMROBERTJOYCE_SETCODEPERCENTERRONEOUSPAIRPROGRAMRJ;
}}
else if (action instanceof PairProgramTimothyRedaAction) {
if (name.equals("IncCodeCompletenessPairProgramTR")) {
text = RuleDescriptions.PAIRPROGRAMTIMOTHYREDA_INCCODECOMPLETENESSPAIRPROGRAMTR;
}else if (name.equals("SetCodePercentErroneousPairProgramTR")) {
text = RuleDescriptions.PAIRPROGRAMTIMOTHYREDA_SETCODEPERCENTERRONEOUSPAIRPROGRAMTR;
}}
else if (action instanceof PairProgramPegSigfreidoAction) {
if (name.equals("IncCodeCompletenessPairProgramPS")) {
text = RuleDescriptions.PAIRPROGRAMPEGSIGFREIDO_INCCODECOMPLETENESSPAIRPROGRAMPS;
}else if (name.equals("SetCodePercentErroneousPairProgramPS")) {
text = RuleDescriptions.PAIRPROGRAMPEGSIGFREIDO_SETCODEPERCENTERRONEOUSPAIRPROGRAMPS;
}}
else if (action instanceof UnitTestingAndFixingAction) {
if (name.equals("RemoveErrorsFromCode")) {
text = RuleDescriptions.UNITTESTINGANDFIXING_REMOVEERRORSFROMCODE;
}}
else if (action instanceof RefactorAction) {
if (name.equals("IncPercentRefactored")) {
text = RuleDescriptions.REFACTOR_INCPERCENTREFACTORED;
}else if (name.equals("IncNumErrorsRefactor")) {
text = RuleDescriptions.REFACTOR_INCNUMERRORSREFACTOR;
}}
else if (action instanceof IntegrateRobertJoyceAction) {
if (name.equals("IncPercentIntegratedCodeRJ")) {
text = RuleDescriptions.INTEGRATEROBERTJOYCE_INCPERCENTINTEGRATEDCODERJ;
}else if (name.equals("IncNumErrorsIntegrateRJ")) {
text = RuleDescriptions.INTEGRATEROBERTJOYCE_INCNUMERRORSINTEGRATERJ;
}}
else if (action instanceof IntegrateTimothyRedaAction) {
if (name.equals("IncPercentIntegratedCodeTR")) {
text = RuleDescriptions.INTEGRATETIMOTHYREDA_INCPERCENTINTEGRATEDCODETR;
}else if (name.equals("IncNumErrorsIntegrateTR")) {
text = RuleDescriptions.INTEGRATETIMOTHYREDA_INCNUMERRORSINTEGRATETR;
}}
else if (action instanceof IntegratePegSigfreidoAction) {
if (name.equals("IncPercentIntegratedCodePS")) {
text = RuleDescriptions.INTEGRATEPEGSIGFREIDO_INCPERCENTINTEGRATEDCODEPS;
}else if (name.equals("IncNumErrorsIntegratePS")) {
text = RuleDescriptions.INTEGRATEPEGSIGFREIDO_INCNUMERRORSINTEGRATEPS;
}}
else if (action instanceof IntegrateAction) {
if (name.equals("IncPercentIntegratedCode")) {
text = RuleDescriptions.INTEGRATE_INCPERCENTINTEGRATEDCODE;
}else if (name.equals("IncNumErrorsIntegrate")) {
text = RuleDescriptions.INTEGRATE_INCNUMERRORSINTEGRATE;
}}
else if (action instanceof AcceptanceTestingAction) {
if (name.equals("IncAcceptanceTestRun")) {
text = RuleDescriptions.ACCEPTANCETESTING_INCACCEPTANCETESTRUN;
}else if (name.equals("SetNumFailedTests")) {
text = RuleDescriptions.ACCEPTANCETESTING_SETNUMFAILEDTESTS;
}else if (name.equals("SetCodePcntErroneousAccTesting")) {
text = RuleDescriptions.ACCEPTANCETESTING_SETCODEPCNTERRONEOUSACCTESTING;
}}
else if (action instanceof ReleaseCodeAndEndIterationAction) {
}
else if (action instanceof RequireReleasePlanDoOverAction) {
if (name.equals("ResetReleasePlanCompleteness")) {
text = RuleDescriptions.REQUIRERELEASEPLANDOOVER_RESETRELEASEPLANCOMPLETENESS;
}else if (name.equals("DeactivateOtherActions")) {
text = RuleDescriptions.REQUIRERELEASEPLANDOOVER_DEACTIVATEOTHERACTIONS;
}else if (name.equals("ReactivateOtherActions")) {
text = RuleDescriptions.REQUIRERELEASEPLANDOOVER_REACTIVATEOTHERACTIONS;
}}
else if (action instanceof CustomerComplainsAction) {
}
else if (action instanceof AllEmployeesIdleAction) {
}
else if (action instanceof DeliverFinalProductToCustomerAction) {
}
else if (action instanceof GameOverAction) {
if (name.equals("CalculateScore")) {
text = RuleDescriptions.GAMEOVER_CALCULATESCORE;
}}
descriptionArea.setText(text);
descriptionArea.setCaretPosition(0);
}
}
}