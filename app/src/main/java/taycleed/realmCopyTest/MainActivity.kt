package taycleed.realmCopyTest

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var sumManT01: Long = 0
    private var sumManT02: Long = 0
    private var sumUnmanT01: Long = 0
    private var sumUnmanT02: Long = 0
    private var count: Int = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val textViewSum = findViewById<TextView>(R.id.textView_summary)
        textView.text = ""
        textViewSum.text = ""

        val btnClear = findViewById<Button>(R.id.button2)
        btnClear.setOnClickListener {
            textView.text = ""
            textViewSum.text = ""
            sumManT01 = 0
            sumManT02 = 0
            sumUnmanT01 = 0
            sumUnmanT02 = 0
            count = 0
        }

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            ManagedRealmRunner.getInstance().init()
            ManagedRealmRunner.getInstance().peek()
            ManagedRealmRunner.getInstance().peekBig()

            val manT01 = ManagedRealmRunner.getInstance().testRun01()
            ManagedRealmRunner.getInstance().peek()
            val unmanT01 = UnmanagedRealmRunner.getInstance().testRun01()
            ManagedRealmRunner.getInstance().peek()
            val manT02 = ManagedRealmRunner.getInstance().testRun02()
            ManagedRealmRunner.getInstance().peekBig()
            val unmanT02 = UnmanagedRealmRunner.getInstance().testRun02()
            ManagedRealmRunner.getInstance().peekBig()

            textView.text = textView.text as String +
                    "Managed: $manT01 ms\n" +
                    "Copying: $unmanT01 ms\n" +
                    "Managed(Big): $manT02 ms\n" +
                    "Copying(Big): $unmanT02 ms\n" + "\n"

            updateSummary(manT01, manT02, unmanT01, unmanT02)
        }
    }

    private fun updateSummary(manT01: Long, manT02: Long, unmanT01: Long, unmanT02: Long) {
        sumManT01 += manT01
        sumManT02 += manT02
        sumUnmanT01 += unmanT01
        sumUnmanT02 += unmanT02
        count++

        val textViewSum = findViewById<TextView>(R.id.textView_summary)
        textViewSum.text = String.format(
            Locale.US, "%s\n%s\n%s\n%s\n\n",
            "Avg. Managed: ${sumManT01 / count} ms",
            "Avg. Copying: ${sumUnmanT01 / count} ms",
            "Avg. Managed(Big): ${sumManT02 / count} ms",
            "Avg. Copying(Big): ${sumUnmanT02 / count} ms"
        )
    }
}

