package com.active.constant;

public enum State {
	
	BEGIN("begin"), PROGRESS("progress"), COMPLETED("completed");
	
	private String state;
    
    State(String state){
        this.state = state;
    }
   
    public String getstate(){
        return state;
    }
}