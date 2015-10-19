/* File generated by: simse.codegenerator.logicgenerator.dialoggenerator.ParticipantSelectionDialogsDriverGenerator */
package simse.logic.dialogs;
import simse.state.*;
import simse.adts.objects.*;
import simse.adts.actions.*;
import simse.logic.*;
import simse.gui.*;
import java.util.*;
import javax.swing.*;
public class ParticipantSelectionDialogsDriver
{
private Vector<String> partNames;
private Vector<Vector<? extends SSObject>> partsVector;
private simse.adts.actions.Action action;
private State state;
private Employee selectedEmp;
private RuleExecutor ruleExec;
private DestroyerChecker destChecker;
private String menuText;
public ParticipantSelectionDialogsDriver(JFrame gui, Vector<String> pNames, Vector<Vector<? extends SSObject>> parts, simse.adts.actions.Action act, State s, RuleExecutor re, DestroyerChecker dc, Employee emp, String mText)
{
partNames = pNames;
partsVector = parts;
action = act;
state = s;
selectedEmp = emp;
ruleExec = re;
destChecker = dc;
menuText = mText;
boolean actionValid = true;
for(int i=0; i<partNames.size(); i++)
{
String participantName = partNames.elementAt(i);
Vector<? extends SSObject> participants = partsVector.elementAt(i);
// check to see if any of these possible participants have already been added to the action in a different role:
Vector<SSObject> allParts = action.getAllParticipants();
Enumeration participantsEnum = participants.elements();
while(participantsEnum.hasMoreElements())
{
SSObject tempObj = (SSObject)participantsEnum.nextElement();
for(int k=0; k<allParts.size(); k++)
{
SSObject tempObj2 = allParts.elementAt(k);
if(tempObj == tempObj2)
{
participants.remove(tempObj);
break;
}
}
}
if((participants.size() == 0) || (participants.elementAt(0) instanceof Employee))
{
for(int j=0; j<allParts.size(); j++)
{
SSObject tempObj = allParts.elementAt(j);
if((selectedEmp != null) && (tempObj == selectedEmp))
{
selectedEmp = null;
break;
}
}
boolean participantsContainsSelEmp = false;
Iterator participantsIterator = participants.iterator();
while(participantsIterator.hasNext())
{
SSObject tempObj = (SSObject)participantsIterator.next();
if(tempObj == selectedEmp)
{
participantsContainsSelEmp = true;
break;
}
}

if((selectedEmp != null) && (participantsContainsSelEmp)) // selectedEmp needs to be added to the action as one of these participants
{
participants.remove(selectedEmp);
EmployeeParticipantSelectionDialog psd = new EmployeeParticipantSelectionDialog(gui, participantName, new Vector<SSObject>(participants), action, state, selectedEmp);
if(psd.actionCancelled())
{
actionValid = false;
break;
}
}
else // pass null in instead of selectedEmp
{
EmployeeParticipantSelectionDialog psd = new EmployeeParticipantSelectionDialog(gui, participantName, new Vector<SSObject>(participants), action, state, null);
if(psd.actionCancelled())
{
actionValid = false;
break;
}
}
}
else
{
NonEmployeeParticipantSelectionDialog psd = new NonEmployeeParticipantSelectionDialog(gui, participantName, new Vector<SSObject>(participants), action, state);
if(psd.actionCancelled())
{
actionValid = false;
break;
}
}
}
if(actionValid)
{
if(action instanceof CreateUserStoriesAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Create user stories"))
{
((Employee)obj).setOverheadText("We are about to create user stories--short descriptions of what the system should do. Monitor the user stories artifact to track our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Create user stories"))
{
((Customer)obj).setOverheadText("We are about to create user stories--short descriptions of what the system should do. Monitor the user stories artifact to track our progress.");
}
}
}
state.getActionStateRepository().getCreateUserStoriesActionStateRepository().add((CreateUserStoriesAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleCreateUST", action);
destChecker.update(false, gui);
}
else if(action instanceof ReleasePlanningMeetingAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Hold release planning meeting"))
{
((Employee)obj).setOverheadText("We are starting the release planning meeting, in which we prioritize user stories and determine how long each one will take to develop. View the release plan artifact to track our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Hold release planning meeting"))
{
((Customer)obj).setOverheadText("We are starting the release planning meeting, in which we prioritize user stories and determine how long each one will take to develop. View the release plan artifact to track our progress.");
}
}
}
state.getActionStateRepository().getReleasePlanningMeetingActionStateRepository().add((ReleasePlanningMeetingAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetManagementInvolved", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetCustomerInvolved", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleReleaseMtgT", action);
destChecker.update(false, gui);
}
else if(action instanceof StartIterationAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Start iteration"))
{
((Employee)obj).setOverheadText("We have started a new iteration. What would you like us to do now??");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Start iteration"))
{
((Customer)obj).setOverheadText("We have started a new iteration. What would you like us to do now??");
}
}
}
state.getActionStateRepository().getStartIterationActionStateRepository().add((StartIterationAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetIterationStarted", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNumUSIntegratedBeginIteration", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetPercentErroneousBeginIteration", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "ResetArtifactAndEmpVals", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "ResetTimeSinceLastRelease", action);
destChecker.update(false, gui);
}
else if(action instanceof IterationPlanningMeetingAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Hold iteration planning meeting"))
{
((Employee)obj).setOverheadText("We're beginning the iteration planning meeting. Monitor the current iteration plan artifact to view our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Hold iteration planning meeting"))
{
((Customer)obj).setOverheadText("We're beginning the iteration planning meeting. Monitor the current iteration plan artifact to view our progress.");
}
}
}
state.getActionStateRepository().getIterationPlanningMeetingActionStateRepository().add((IterationPlanningMeetingAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetIterationPlanningMeetingStarted", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetCustomerInvolvedItPlanning", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetEmpsInvolvedInMeeting", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleIterationMtgT", action);
destChecker.update(false, gui);
}
else if(action instanceof CreateAcceptanceTestsAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Create acceptance tests"))
{
((Employee)obj).setOverheadText("We are creating the acceptance tests now. Monitor the acceptance tests artifact to see our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Create acceptance tests"))
{
((Customer)obj).setOverheadText("We are creating the acceptance tests now. Monitor the acceptance tests artifact to see our progress.");
}
}
}
state.getActionStateRepository().getCreateAcceptanceTestsActionStateRepository().add((CreateAcceptanceTestsAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetCustInvolvedAccTests", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsCreateATTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleCreateAccTestsT", action);
destChecker.update(false, gui);
}
else if(action instanceof DesignAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Design"))
{
((Employee)obj).setOverheadText("We are designing the user stories for this iteration. Monitor the design artifact to see how we're progressing.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Design"))
{
((Customer)obj).setOverheadText("We are designing the user stories for this iteration. Monitor the design artifact to see how we're progressing.");
}
}
}
state.getActionStateRepository().getDesignActionStateRepository().add((DesignAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsDesignTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleDesignT", action);
destChecker.update(false, gui);
}
else if(action instanceof CreateUnitTestsAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Create unit tests"))
{
((Employee)obj).setOverheadText("We're creating unit tests for each class we designed. You can track our progress by viewing the unit tests artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Create unit tests"))
{
((Customer)obj).setOverheadText("We're creating unit tests for each class we designed. You can track our progress by viewing the unit tests artifact.");
}
}
}
state.getActionStateRepository().getCreateUnitTestsActionStateRepository().add((CreateUnitTestsAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetToolUsedOrNot", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsCreateUTTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleCreateUTT", action);
destChecker.update(false, gui);
}
else if(action instanceof LearnCodingStandardAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Learn coding standard"))
{
((Employee)obj).setOverheadText("I'm off to familiarize myself with the coding standard now.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Learn coding standard"))
{
((Customer)obj).setOverheadText("I'm off to familiarize myself with the coding standard now.");
}
}
}
state.getActionStateRepository().getLearnCodingStandardActionStateRepository().add((LearnCodingStandardAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleLearnStdT", action);
destChecker.update(false, gui);
}
else if(action instanceof ProgramAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Program"))
{
((Employee)obj).setOverheadText("I'm starting to program my parts of the user stories for this iteration now. Monitor the code artifact to track our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Program"))
{
((Customer)obj).setOverheadText("I'm starting to program my parts of the user stories for this iteration now. Monitor the code artifact to track our progress.");
}
}
}
state.getActionStateRepository().getProgramActionStateRepository().add((ProgramAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetProgrammingProgramTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsProgramTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleProgramT", action);
destChecker.update(false, gui);
}
else if(action instanceof PairProgramRobertJoyceAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Pair program (Robert and Joyce)"))
{
((Employee)obj).setOverheadText("We're pair programming now. Monitor the code artifact to track our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Pair program (Robert and Joyce)"))
{
((Customer)obj).setOverheadText("We're pair programming now. Monitor the code artifact to track our progress.");
}
}
}
state.getActionStateRepository().getPairProgramRobertJoyceActionStateRepository().add((PairProgramRobertJoyceAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetProgrammingPairProgramRJTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetRJPairProgThisIteration", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsPairProgRJTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdlePairProgRJT", action);
destChecker.update(false, gui);
}
else if(action instanceof PairProgramTimothyRedaAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Pair Program (Timothy and Reda)"))
{
((Employee)obj).setOverheadText("We're pair programming now. Monitor the code artifact to track our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Pair Program (Timothy and Reda)"))
{
((Customer)obj).setOverheadText("We're pair programming now. Monitor the code artifact to track our progress.");
}
}
}
state.getActionStateRepository().getPairProgramTimothyRedaActionStateRepository().add((PairProgramTimothyRedaAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetProgrammingPairProgramTRTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetTRPairProgThisIteration", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsPairProgTRTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdlePairProgTRT", action);
destChecker.update(false, gui);
}
else if(action instanceof PairProgramPegSigfreidoAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Pair program (Peg and Sigfreido)"))
{
((Employee)obj).setOverheadText("We're pair programming now. Monitor the code artifact to track our progress.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Pair program (Peg and Sigfreido)"))
{
((Customer)obj).setOverheadText("We're pair programming now. Monitor the code artifact to track our progress.");
}
}
}
state.getActionStateRepository().getPairProgramPegSigfreidoActionStateRepository().add((PairProgramPegSigfreidoAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetProgrammingPairProgramPSTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetPSPairProgThisIteration", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsPairProgPSTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdlePairProgPST", action);
destChecker.update(false, gui);
}
else if(action instanceof UnitTestingAndFixingAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Unit test and fix"))
{
((Employee)obj).setOverheadText("I'm going to run unit tests now, and fix any bugs I find. You can see how the quality of the code is improving by monitoring the code artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Unit test and fix"))
{
((Customer)obj).setOverheadText("I'm going to run unit tests now, and fix any bugs I find. You can see how the quality of the code is improving by monitoring the code artifact.");
}
}
}
state.getActionStateRepository().getUnitTestingAndFixingActionStateRepository().add((UnitTestingAndFixingAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "ResetNumAcceptanceTestsRun", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsUTTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleUTFixT", action);
destChecker.update(false, gui);
}
else if(action instanceof RefactorAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Refactor code"))
{
((Employee)obj).setOverheadText("I am refactoring the code now to improve its structure, consistency, and clarity. You can view our progress in this by monitoring the code artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Refactor code"))
{
((Customer)obj).setOverheadText("I am refactoring the code now to improve its structure, consistency, and clarity. You can view our progress in this by monitoring the code artifact.");
}
}
}
state.getActionStateRepository().getRefactorActionStateRepository().add((RefactorAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsRefactor", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleRefactorT", action);
destChecker.update(false, gui);
}
else if(action instanceof IntegrateRobertJoyceAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Pair integrate (Robert and Joyce)"))
{
((Employee)obj).setOverheadText("We are integrating our code with the rest of the system now. You can monitor our progress by viewing the code artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Pair integrate (Robert and Joyce)"))
{
((Customer)obj).setOverheadText("We are integrating our code with the rest of the system now. You can monitor our progress by viewing the code artifact.");
}
}
}
state.getActionStateRepository().getIntegrateRobertJoyceActionStateRepository().add((IntegrateRobertJoyceAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetIntegratingTrigRJ", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsIntegrateRJTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleIntRJT", action);
destChecker.update(false, gui);
}
else if(action instanceof IntegrateTimothyRedaAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Pair integrate (Timothy and Reda)"))
{
((Employee)obj).setOverheadText("We are integrating our code with the rest of the system now. You can monitor our progress by viewing the code artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Pair integrate (Timothy and Reda)"))
{
((Customer)obj).setOverheadText("We are integrating our code with the rest of the system now. You can monitor our progress by viewing the code artifact.");
}
}
}
state.getActionStateRepository().getIntegrateTimothyRedaActionStateRepository().add((IntegrateTimothyRedaAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetIntegratingTrigTR", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsIntegrateTRTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleIntTRT", action);
destChecker.update(false, gui);
}
else if(action instanceof IntegratePegSigfreidoAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Pair integrate (Peg and Sigfreido)"))
{
((Employee)obj).setOverheadText("We are integrating our code with the rest of the system now. You can monitor our progress by viewing the code artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Pair integrate (Peg and Sigfreido)"))
{
((Customer)obj).setOverheadText("We are integrating our code with the rest of the system now. You can monitor our progress by viewing the code artifact.");
}
}
}
state.getActionStateRepository().getIntegratePegSigfreidoActionStateRepository().add((IntegratePegSigfreidoAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetIntegratingTrigPS", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsIntegratePSTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleIntPST", action);
destChecker.update(false, gui);
}
else if(action instanceof IntegrateAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Integrate"))
{
((Employee)obj).setOverheadText("I'm integrating code now.  You can monitor our progress by viewing the code artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Integrate"))
{
((Customer)obj).setOverheadText("I'm integrating code now.  You can monitor our progress by viewing the code artifact.");
}
}
}
state.getActionStateRepository().getIntegrateActionStateRepository().add((IntegrateAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetIntegratingTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "UpdateNumActsIntegrateTrig", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleIntegrateT", action);
destChecker.update(false, gui);
}
else if(action instanceof AcceptanceTestingAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Acceptance testing"))
{
((Employee)obj).setOverheadText("I'm running the acceptance tests with the customer now, to see if this release meets their expectations. You can monitor the progress of these tests by looking at the acceptance tests artifact.");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Acceptance testing"))
{
((Customer)obj).setOverheadText("I'm running the acceptance tests with the customer now, to see if this release meets their expectations. You can monitor the progress of these tests by looking at the acceptance tests artifact.");
}
}
}
state.getActionStateRepository().getAcceptanceTestingActionStateRepository().add((AcceptanceTestingAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetAcceptanceTesting", action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetNotIdleAccTestT", action);
destChecker.update(false, gui);
}
else if(action instanceof ReleaseCodeAndEndIterationAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
if(menuText.equals("Release code and end iteration"))
{
((Employee)obj).setOverheadText("We have released the current system and ended this iteration. What next???");
}
}
else if(obj instanceof Customer)
{
if(menuText.equals("Release code and end iteration"))
{
((Customer)obj).setOverheadText("We have released the current system and ended this iteration. What next???");
}
}
}
state.getActionStateRepository().getReleaseCodeAndEndIterationActionStateRepository().add((ReleaseCodeAndEndIterationAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "ResetProjectAttributes", action);
destChecker.update(false, gui);
}
else if(action instanceof DeliverFinalProductToCustomerAction)
{
Vector<SSObject> participants = action.getAllParticipants();
for(int i=0; i<participants.size(); i++)
{
SSObject obj = participants.elementAt(i);
if(obj instanceof Employee)
{
}
else if(obj instanceof Customer)
{
}
}
state.getActionStateRepository().getDeliverFinalProductToCustomerActionStateRepository().add((DeliverFinalProductToCustomerAction)action);
ruleExec.update(gui, RuleExecutor.UPDATE_ONE, "SetPerfectScore", action);
destChecker.update(false, gui);
if(menuText.equals("Deliver final product to customer"))
{
// stop game and give score:
DeliverFinalProductToCustomerAction a = (DeliverFinalProductToCustomerAction)action;
if(a.getAllProjs().size() > 0)
{
TheProject t = (TheProject)(a.getAllProjs().elementAt(0));
if(t != null)
{
double v = t.getScore();
state.getClock().stop();
state.setScore(v);
((SimSEGUI)gui).update();
JOptionPane.showMessageDialog(null, ("Your score is " + v), "Game over!", JOptionPane.INFORMATION_MESSAGE);
}
}
}
}
}
}
}