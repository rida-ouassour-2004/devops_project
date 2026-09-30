package com.rida.task_api.dto;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title ;}
    public String getDescription(){
        return this.description;
    }
    public boolean getCompleted(){
        return this.completed;
    }
    public void  setId(Long id ){
            this.id=id;
        }
        public void setTitle(String title){
        this.title=title;
        }
        public void setDescription(String description){
        this.description=description;
        }
        public void setCompleted(boolean completed){
        this.completed=completed;
        }
}
