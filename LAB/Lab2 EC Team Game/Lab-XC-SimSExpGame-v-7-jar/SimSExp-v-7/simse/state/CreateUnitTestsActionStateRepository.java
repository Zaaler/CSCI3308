/* File generated by: simse.codegenerator.stategenerator.RepositoryGenerator */
package simse.state;
import simse.adts.objects.*;
import simse.adts.actions.*;
import java.util.*;

public class CreateUnitTestsActionStateRepository implements Cloneable
{
private Vector<CreateUnitTestsAction> actions;

public CreateUnitTestsActionStateRepository()
{
actions = new Vector<CreateUnitTestsAction>();
}

public Object clone() {
try {
CreateUnitTestsActionStateRepository cl = (CreateUnitTestsActionStateRepository) (super.clone());
Vector<CreateUnitTestsAction> clonedActions = new Vector<CreateUnitTestsAction>();
for (int i = 0; i < actions.size(); i++) {
clonedActions.add((CreateUnitTestsAction)actions.elementAt(i).clone());
}
cl.actions = clonedActions;
return cl;
} catch (CloneNotSupportedException c) {
System.out.println(c.getMessage());
}
return null;
}

public boolean add(CreateUnitTestsAction a)
{
if(actions.contains(a) == false)
{
actions.add(a);
return true;
}
return false;
}

public boolean remove(CreateUnitTestsAction a)
{
if(actions.contains(a))
{
actions.remove(a);
return true;
}
return false;
}

public Vector<CreateUnitTestsAction> getAllActions()
{
return actions;
}

public Vector<CreateUnitTestsAction> getAllActions(SSObject a)
{
Vector<CreateUnitTestsAction> all = new Vector<CreateUnitTestsAction>();
for(int i=0; i<actions.size(); i++)
{
CreateUnitTestsAction b = actions.elementAt(i);
Vector<SSObject> parts = b.getAllParticipants();
for(int j=0; j<parts.size(); j++)
{
if(parts.elementAt(j).equals(a))
{
all.add(b);
break;
}
}
}
return all;
}

public Vector<CreateUnitTestsAction> getAllActiveActions(SSObject a)
{
Vector<CreateUnitTestsAction> all = new Vector<CreateUnitTestsAction>();
for(int i=0; i<actions.size(); i++)
{
CreateUnitTestsAction b = actions.elementAt(i);
Vector<SSObject> parts = b.getAllActiveParticipants();
for(int j=0; j<parts.size(); j++)
{
if(parts.elementAt(j).equals(a))
{
all.add(b);
break;
}
}
}
return all;
}

public Vector<CreateUnitTestsAction> getAllInactiveActions(SSObject a)
{
Vector<CreateUnitTestsAction> all = new Vector<CreateUnitTestsAction>();
for(int i=0; i<actions.size(); i++)
{
CreateUnitTestsAction b = actions.elementAt(i);
Vector<SSObject> parts = b.getAllInactiveParticipants();
for(int j=0; j<parts.size(); j++)
{
if(parts.elementAt(j).equals(a))
{
all.add(b);
break;
}
}
}
return all;
}

public CreateUnitTestsAction getActionWithId(int id) {
for (int i = 0; i < actions.size(); i++) {
CreateUnitTestsAction act = actions.get(i);
if (act.getId() == id) {
return act;
}
}
return null;
}

/*
* Replaces all the participants in each action with their equivalent objects
* in the current state. Calling this function solves the problem that
* happens when you clone actions -- their hashtables point to participant
* objects that were part of the previous, non-cloned state.
* Hence, this function should be called after this object is cloned.
*/
public void refetchParticipants(ArtifactStateRepository artifactRep, CustomerStateRepository customerRep, EmployeeStateRepository employeeRep, ProjectStateRepository projectRep, ToolStateRepository toolRep) {
for (int i = 0; i < actions.size(); i++) {
CreateUnitTestsAction act = actions.elementAt(i);
act.refetchParticipants(artifactRep, customerRep, employeeRep, projectRep, toolRep);}
}
}