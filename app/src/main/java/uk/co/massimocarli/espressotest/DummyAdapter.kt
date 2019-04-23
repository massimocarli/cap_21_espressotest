package uk.co.massimocarli.espressotest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class DummyAdapter(val model: List<Model>, val listener: OnModelSelectedCallback? = null) :
  RecyclerView.Adapter<DummyViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
    return DummyViewHolder(view, listener);
  }

  override fun getItemCount(): Int = model.size

  override fun onBindViewHolder(holder: DummyViewHolder, position: Int) =
    holder.bind(model[position])
}