package com.orangeskill.elate.feature.session;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.orangeskill.elate.R;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.Program;
import com.orangeskill.elate.feature.session.data.ExpdListDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpdListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ExpdListDataSource.Group> expandableListTitle;
    private HashMap<ExpdListDataSource.Group, List<Program>> expandableListDetail;

    public ExpdListAdapter(Context context, ArrayList<ExpdListDataSource.Group> expandableListTitle, HashMap<ExpdListDataSource.Group, List<Program>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Program expandedListText = (Program) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expd_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.subTitle);
        expandedListTextView.setText(expandedListText.getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ExpdListDataSource.Group group = (ExpdListDataSource.Group) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expd_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        TextView sessionText = convertView.findViewById(R.id.session_txt);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(group.title);
        sessionText.setText(String.valueOf(group.size + " Session"));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
