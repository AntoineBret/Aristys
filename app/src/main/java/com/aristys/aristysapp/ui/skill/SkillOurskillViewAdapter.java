package com.aristys.aristysapp.ui.skill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Skill;
import com.bumptech.glide.Glide;

import java.util.List;

public class SkillOurskillViewAdapter extends RecyclerView.Adapter <SkillOurskillViewAdapter.ViewHolder> {

  private Context context;

  public List<Skill> skillList;

  private LayoutInflater inflater = null;

  public SkillOurskillViewAdapter(Context context, List <Skill> skillList) {
    this.context = context;
    this.skillList = skillList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public SkillOurskillViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.skillourskill_cardview, viewGroup, false);
    return new SkillOurskillViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SkillOurskillViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Skill skill = skillList.get(i);
    Glide.with(context).load(skill.getSkillthumbnail()).into(holder.skill_thumbnail);
    holder.skill_title.setText(skill.getSkilltitle());
  }

  @Override
  public int getItemCount() {
    return skillList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView skill_title;
    private ImageView skill_thumbnail;

    public ViewHolder(View view) {
      super(view);

      skill_title = (TextView) view.findViewById(R.id.skill_title);
      skill_thumbnail = (ImageView) view.findViewById(R.id.skill_thumbnail);
    }
  }
}
