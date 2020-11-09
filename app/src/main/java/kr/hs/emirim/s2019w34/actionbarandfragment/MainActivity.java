package kr.hs.emirim.s2019w34.actionbarandfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{
    ActionBar.Tab tabHowl, tabMaltifu, tabPome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabHowl = bar.newTab();
        tabHowl.setText("하울");
        tabHowl.setTabListener(tabListener);
        bar.addTab(tabHowl);

        tabMaltifu = bar.newTab();
        tabMaltifu.setText("말티푸");
        tabMaltifu.setTabListener(tabListener);
        bar.addTab(tabMaltifu);

        tabPome = bar.newTab();
        tabPome.setText("포메라니안");
        tabPome.setTabListener(tabListener);
        bar.addTab(tabPome);
    }

    MyFragment myFrags[] = new MyFragment[3];

    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            MyFragment myFrag = null;
            if(myFrags[tab.getPosition()] == null) {
                myFrag = new MyFragment();
                Bundle data = new Bundle();
                data.putString("tabName", tab.getText().toString());
                myFrag.setArguments(data);
                myFrags[tab.getPosition()] = myFrag;
            }else{
                myFrag = myFrags[tab.getPosition()];
            }

            ft.replace(android.R.id.content, myFrag);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };

    public static class MyFragment extends Fragment{
        String tabName;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout linear = new LinearLayout(super.getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            linear.setLayoutParams(params);
            linear.setOrientation(LinearLayout.VERTICAL);
            if(tabName.equals("하울"))
                linear.setBackgroundColor(Color.RED);
            if(tabName.equals("말티푸"))
                linear.setBackgroundColor(Color.YELLOW);
            if(tabName.equals("포메라니안"))
                linear.setBackgroundColor(Color.BLUE);

            return linear;
        }
    }
}