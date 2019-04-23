package uk.co.massimocarli.espressotest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*

typealias OnModelSelectedCallback = (Model) -> Unit

class DummyViewHolder(val view: View, val callback: OnModelSelectedCallback? = null) : RecyclerView.ViewHolder(view) {

  private var currentModel: Model? = null

  init {
    view.setOnClickListener {
      currentModel?.let {
        callback?.invoke(it)
      }
    }
  }

  fun bind(model: Model) {
    currentModel = model
    view.textPosition.text = "#${model.pos}"
    view.textValue.text = "#${model.textValue}"
  }
}