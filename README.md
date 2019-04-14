# HistoricChanges Java Class

Class to implement Undo/Redo system on Java projects

---------------
## Install

Just copy the class file HistoricChanges.java in your java project scripts folder.

---------------
## How To Use

Create an instance of a HistoricChanges, set the desired events, and you are ready to call functions to start a server or client.

* __Functions__
```java
HistoricChanges hc = new HistoricChanges(maxChange);

//Add new change withi name, undo Runnable and redo Runnable
hc.addChange(name,undoRunnable,redoRunnable);

//Execute next undo Runnable action
hc.undo();

//Execute next redo Runnable action
hc.redo();

String lastChangeName = hc.getLastChangeName();

String indexChangeName = hc.getIndexChangeName();

String[] allChangeNames = hc.getAllChangeName();

hc.clear();

```
