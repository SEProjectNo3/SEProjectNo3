package com.active.model;
import java.util.ArrayList;

public class Lecture 
{
	private String lectureId;
	private String title;
	private String explanation;
	private String filePath;
	private int hits;
	private ArrayList<String> materialList;
	
	public String getLectureId() {
		return lectureId;
	}
	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public ArrayList<String> getMaterialList() {
		return materialList;
	}
	public void setMaterialList(ArrayList<String> materialList) {
		this.materialList = materialList;
	}
}
