class HistoricChanges{
	private List<AbstractMap.SimpleEntry<String, Runnable[]>> changes = new ArrayList<AbstractMap.SimpleEntry<String,Runnable[]>>();
	private int index = 0;
	private int maxChanges = 10;
	public HistoricChanges(int maxChanges){
		this.maxChanges=maxChanges;
	}
	public String getLastChangeName(){return changes.get(changes.size()-1).getKey();}
	public String getIndexChangeName(){return changes.get(index).getKey();}
	public String[] getAllChangeName(){
		String[] names= new String[changes.size()];
		for(int i = 0; i < changes.size(); i++){
			AbstractMap.SimpleEntry<String, Runnable[]> entry = changes.get(i);
			names[i]= entry.getKey();
		}
		return names;
	}
	
	public void addChange(String name, Runnable undoAction, Runnable redoAction){
		Runnable[] change = new Runnable[2];
		change[0] = undoAction;
		change[1] = redoAction;
		synchronized(changes){
			if(index<changes.size()){
				changes = changes.subList(0,index-1);
			}
			if(changes.size()==this.maxChanges){
				changes.remove(0);
			}
			changes.add(new AbstractMap.SimpleEntry<String,Runnable[]>(name,  change));
			index = changes.size();
		}
	}
	public boolean isUndable(){
		return index > 0;
	}
	public boolean isRedable(){
		return index < changes.size();
	}
	public boolean undo(){
		if(isUndable()){
			index--;
			changes.get(index).getValue()[0].run();
		}
		
		return isUndable();
	}
	public boolean redo(){
		if(isRedable()){
			changes.get(index).getValue()[1].run();
			index++;
		}

		return isRedable();
	}
}