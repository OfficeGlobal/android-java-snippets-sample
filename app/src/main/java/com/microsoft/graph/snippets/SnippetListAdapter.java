/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.graph.snippets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.microsoft.graph.snippets.snippet.AbstractSnippet;
import com.microsoft.graph.snippets.snippet.SnippetContent;

public class SnippetListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    @Override
    public int getCount() {
        return SnippetContent.ITEMS.size();
    }

    @Override
    public AbstractSnippet<?> getItem(int position) {
        return (AbstractSnippet) SnippetContent.ITEMS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        return null != getItem(position).getDescription();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == mContext) {
            mContext = parent.getContext();
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        AbstractSnippet<?> clickedSnippet = getItem(position);
        boolean isSegment = (null == clickedSnippet.getDescription());

        final int id = isSegment ? R.layout.list_segment : R.layout.list_element;
        if (null == convertView || isWrongViewType(isSegment, convertView)) {
            convertView = mLayoutInflater.inflate(id, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.txt_snippet_name);
        name.setText(clickedSnippet.getName());

        //Set text to indicate if Admin account is required to run the snippet
        if (!isSegment) {
            TextView adminIndicator = (TextView) convertView.findViewById(R.id.admin_indicator);
            if (adminIndicator != null) {
                if (clickedSnippet.getIsAdminRequired()) {
                    //Admin account required
                    adminIndicator.setText(R.string.admin);
                    adminIndicator.setVisibility(View.VISIBLE);
                } else {
                    //Admin account not required
                    adminIndicator.setVisibility(View.GONE);
                }
            }
        }
        return convertView;
    }

    private boolean isWrongViewType(boolean isSegment, View convertView) {
        View v = convertView.findViewById(R.id.admin_indicator);
        return !isSegment && null == v || (isSegment && null != v);
    }
}