package MP.Inventory;

public class Resource {
    private int value;

    public int getResourceValue(){
        return this.value;
    }
    //To add resource value
    public void addResourceValue(int add){
        this.value= value + add;
    }
    //To subtract resource value
    public void subtractResourceValue(int subtract){
        this.value= value - subtract;
    }

    public Resource(int value) {
        this.value= value;
    }

}
