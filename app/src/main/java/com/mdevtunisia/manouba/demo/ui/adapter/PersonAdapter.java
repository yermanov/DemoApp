package com.mdevtunisia.manouba.demo.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdevtunisia.manouba.demo.R;
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.util.StringUtil;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by Sarra on 10/04/2016.
 */
public class PersonAdapter extends BaseAdapter {

    private List<Person> personList;
    private Context context;
    private LayoutInflater inflater;

    public PersonAdapter(Context context, List<Person> personList)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.personList = personList;
    }

    @Override
    public int getCount()
    {
        return personList == null ? 0 : personList.size();
    }

    @Override
    public Person getItem(int position)
    {
        return personList.get(position) == null ? null : personList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        PersonViewHolder holder = null;
        if (convertView == null) {
            holder = new PersonViewHolder();
            convertView = inflater.inflate(R.layout.person_list_item, parent, false);
            holder.imagePerson = (ImageView) convertView.findViewById(R.id.iv_person);
            holder.firstName = (TextView) convertView.findViewById(R.id.tv_firstname);
            holder.lastName = (TextView) convertView.findViewById(R.id.tv_lastname);
            holder.age = (TextView) convertView.findViewById(R.id.tv_age);
            convertView.setTag(holder);
        } else {
            holder = (PersonViewHolder) convertView.getTag();
        }

        Person person = getItem(position);

        String pictureUrl = person.getPicture();

        if (StringUtil.isEmpty(pictureUrl) == false) {
            Picasso.with(context)
                    .load(pictureUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(holder.imagePerson);
        }

        holder.firstName.setText(person.getFirstName());
        holder.lastName.setText(person.getLastName());
        holder.age.setText("" + person.getAge());
        return convertView;
    }

    static class PersonViewHolder {

        ImageView imagePerson;
        TextView firstName;
        TextView lastName;
        TextView age;
    }
}
