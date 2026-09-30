package com.rida.task_api.dto;
public class TaskRequest {
    private String title ;
    private String description;
    public TaskRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public TaskRequest() {

    }

    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public void setTitle(String title ){
        this.title=title;
    }
    public void setDescription(String description){
        this.description=description ;
    }

}
