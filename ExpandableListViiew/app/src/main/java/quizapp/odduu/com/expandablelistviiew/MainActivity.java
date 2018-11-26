package quizapp.odduu.com.expandablelistviiew;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(MainActivity.this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        //expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
                //Open my custom activity

                // (a) ARTS DIVISION START
                //01. Arabic and Islamic Studies Department
                if (detailInfo.getName().equals("Arabic and Islamic Studies")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //02. Bengali Department
                if (detailInfo.getName().equals("Bengali")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //03. English Department
                if (detailInfo.getName().equals("English")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //04. History Department
                if (detailInfo.getName().equals("History")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //05. Islamic History and Culture Department
                if (detailInfo.getName().equals("Islamic History and Culture")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //06.  Philosophy Department
                if (detailInfo.getName().equals("Philosophy")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //07. Sanskrit Department
                if (detailInfo.getName().equals("Sanskrit")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //08. Urdu Department
                if (detailInfo.getName().equals("Urdu")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //(a) ARTS DIVISION END


                //(b)BUSINESS STUDIES  DIVISION START
                //01. Accounting
                if (detailInfo.getName().equals("Accounting")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //02. Finance and Banking
                if (detailInfo.getName().equals("Finance and Banking")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //03. Management
                if (detailInfo.getName().equals("Management")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //04. Marketing
                if (detailInfo.getName().equals("Marketing")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }
                //(b)BUSINESS STUDIES  DIVISION END


                // (c) SCIENCE DIVISION START
                //01. Botany Department
                if (detailInfo.getName().equals("Botany")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //02. Chemistry Department
                if (detailInfo.getName().equals("Chemistry")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //03. Geography and Environment Department
                if (detailInfo.getName().equals("Geography and Environment")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //04. Mathematics Department
                if (detailInfo.getName().equals("Mathematics")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //05. Physics Department
                if (detailInfo.getName().equals("Physics")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //06. Psychology Department
                if (detailInfo.getName().equals("Psychology")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }


                //07. Statistics Department
                if (detailInfo.getName().equals("Statistics")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //08. Zoology Department
                if (detailInfo.getName().equals("Zoology")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }
                // (c) SCIENCE DIVISION START


                // (d) SOCIAL SCIENCE DIVISION START
                //01. Economics
                if (detailInfo.getName().equals("Economics")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //02. Political Science
                if (detailInfo.getName().equals("Political Science")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //03. Social Work
                if (detailInfo.getName().equals("Social Work")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //04. Sociology
                if (detailInfo.getName().equals("Sociology")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }
                // (d) SOCIAL SCIENCE DIVISION END

                //ICT Course START
                //01. NU One Year ICT Course
                if (detailInfo.getName().equals("National University One Year ICT Course")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }

                //02. Rajshahi College ICT Course
                if (detailInfo.getName().equals("Rajshahi College Own ICT Course")) {
                    Toast.makeText(MainActivity.this, "Clicked on: " + detailInfo.getName(), Toast.LENGTH_SHORT).show();
                }
                //ICT Course END
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {
        addProduct("Arts", "Arabic and Islamic Studies");
        addProduct("Arts", "Bengali");
        addProduct("Arts", "English");
        addProduct("Arts", "History");
        addProduct("Arts", "Islamic History and Culture");
        addProduct("Arts", "Philosophy");
        addProduct("Arts", "Sanskrit");
        addProduct("Arts", "Urdu");

        addProduct("Business studies", "Accounting");
        addProduct("Business studies", "Finance and Banking");
        addProduct("Business studies", "Management");
        addProduct("Business studies", "Marketing");

        addProduct("Science", "Botany");
        addProduct("Science", "Chemistry");
        addProduct("Science", "Geography and Environment");
        addProduct("Science", "Mathematics");
        addProduct("Science", "Physics");
        addProduct("Science", "Psychology");
        addProduct("Science", "Statistics");
        addProduct("Science", "Zoology");

        addProduct("Social Science", "Economics");
        addProduct("Social Science", "Political Science");
        addProduct("Social Science", "Social Work");
        addProduct("Social Science", "Sociology");

        addProduct("ICT", "National University One Year ICT Course");
        addProduct("ICT", "Rajshahi College Own ICT Course");
    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

}
