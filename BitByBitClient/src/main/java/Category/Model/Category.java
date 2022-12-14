package Category.Model;

import java.util.Calendar;

public class Category {
    
    private Integer CategoryID;
    private String Name;
    private Calendar dateAdded;
    
    public Category() {
    }

    public Category(Integer CategoryID, String Name, Calendar dateAdded) {
        this.CategoryID = CategoryID;
        this.Name = Name;
        this.dateAdded = dateAdded;
    }

    public Category(String Name) {
        this.Name = Name;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    @Override
    public String toString() {
        return "Category{" + "CategoryID=" + CategoryID + ", Name=" + Name + ", dateAdded=" + dateAdded + '}';
    }
    
    

    
    
    
    
    
    
    
    
    

}