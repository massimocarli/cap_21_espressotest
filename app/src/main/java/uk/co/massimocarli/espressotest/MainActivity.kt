package uk.co.massimocarli.espressotest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  companion object {
    const val REQUEST_CODE = 37
  }

  val model: List<Model> = List<Model>(100) {
    Model(it, "Value in position #${it}")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val adapter = DummyAdapter(model) {
      if (it.pos == 3) {
        startActivity(Intent().apply {
          action = Intent.ACTION_MAIN
          addCategory(Intent.CATEGORY_LAUNCHER)
          putExtra("NAME", "VALUE")
        })
      } else if (it.pos == 5) {
        startActivityForResult(Intent().apply {
          action = "MyAction"
        }, REQUEST_CODE)
      } else {
        Toast.makeText(this@MainActivity, "Selected ${it.pos}", Toast.LENGTH_SHORT).show()
      }
    }
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adapter
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
      val result = data?.getIntExtra("result", 0)
      Toast.makeText(
        this@MainActivity,
        "Result: ${result}",
        Toast.LENGTH_SHORT
      ).show()
    }
  }
}
