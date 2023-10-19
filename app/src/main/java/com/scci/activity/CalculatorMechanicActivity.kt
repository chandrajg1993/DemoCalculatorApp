package com.scci.activity


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.scci.adapter.CalculatorBonusMechanicAdapter
import com.scci.adapter.CalculatorMechanicAdapter
import com.scci.democalculator.R
import com.scci.democalculator.databinding.ActivityCalculatorMechanicBinding
import com.scci.democalculator.databinding.BottomSheetDialogBinding
import com.scci.repository.HomeViewModel
import com.scci.utlity.CustomAlertDialog
import com.scci.utlity.Utill
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject


@AndroidEntryPoint
class CalculatorMechanicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorMechanicBinding
    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var calculatorMechanicAdapter: CalculatorMechanicAdapter

    @Inject
    lateinit var calculatorBonusMechanicAdapter: CalculatorBonusMechanicAdapter
    var valueData: Double = 0.0
    var totalBonus: Double = 0.0
    var oldValue: Int = 0
    var oldValue1: Int = 0
    var oldValue2: Int = 0
    var oldValue3: Int = 0

    var firstGift: Boolean = true
    var secondGift: Boolean = true
    var thirdGift: Boolean = true

    var firstGift1: Boolean = true
    var secondGift1: Boolean = true
    var thirdGift1: Boolean = true

    var firstGift2: Boolean = true
    var secondGift2: Boolean = true
    var thirdGift2: Boolean = true

    var firstGift3: Boolean = true
    var secondGift3: Boolean = true
    var thirdGift3: Boolean = true

    protected lateinit var customAlertDialog: CustomAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorMechanicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel.MainViewModel()
        customAlertDialog = CustomAlertDialog(this)

        binding.tvMonthlyAmountInWord.visibility = View.GONE
        binding.rvCalculatorMechanic.apply {
            adapter = calculatorMechanicAdapter
            layoutManager = LinearLayoutManager(context)
            calculatorMechanicAdapter.updateUserList(homeViewModel.calculatorListData)

        }

        /*  binding.rvSelectionBonus.apply {
              adapter = calculatorBonusMechanicAdapter
              layoutManager = LinearLayoutManager(context)
              calculatorBonusMechanicAdapter.updateUserList(homeViewModel.calculatorBonusListData)

          }*/
        binding.tvMonthlyAmount.startAnimation(AnimationUtils.loadAnimation(this@CalculatorMechanicActivity,R.anim.scroll_animation) as Animation)

        /*
                calculatorMechanicAdapter.setOnShowGiftClickListener { item,possition ->
                    if (item == 24 || possition != clickPossition) {
                        clickPossition = possition
                        firstGift = true
                        secondGift = true
                        thirdGift = true

                    } else if (firstGift && item == 49) {
                        clickPossition = possition

                        firstGift = false
                        openGiftDialogBox()
                        Utill.ringSoundFail(this@CalculatorMechanicActivity)

                    } else if (secondGift && item == 74) {
                        clickPossition = possition

                        secondGift = false
                        openGiftDialogBox()
                        Utill.ringSoundFail(this@CalculatorMechanicActivity)

                    } else if (thirdGift && item == 100) {
                        clickPossition = possition

                        thirdGift = false
                        openGiftDialogBox()
                        Utill.ringSoundFail(this@CalculatorMechanicActivity)

                    }
                }*/
        calculatorMechanicAdapter.setOnShowGiftClickListener { value,data,possition ->
            try {
                if (value == 24) {
                    if (possition == 0) {
                        firstGift = true
                        secondGift = true
                        thirdGift = true

                    } else if (possition == 1) {
                        firstGift1 = true
                        secondGift1 = true
                        thirdGift1 = true
                    } else if (possition == 2) {
                        firstGift2 = true
                        secondGift2 = true
                        thirdGift2 = true
                    } else if (possition == 3) {

                        firstGift3 = true
                        secondGift3 = true
                        thirdGift3 = true
                    }

                }
                if (possition == 0) {
                    if (data[possition].enterValue < oldValue) {
                        if (value == 49) {
                            firstGift = false
                            secondGift = true
                            thirdGift = true
                        } else if (value == 74) {
                            firstGift = false
                            secondGift = false
                            thirdGift = true
                        }
                    }
                    if (value == 49 && firstGift) {
                        firstGift = false
                        openGiftDialogBox()
                    } else if (value == 74 && secondGift) {
                        secondGift = false
                        openGiftDialogBox()

                    } else if (value == 100 && thirdGift) {
                        thirdGift = false
                        openGiftDialogBox()

                    }
                    oldValue = data[possition].enterValue
                }
                else if (possition == 1) {
                    if (data[possition].enterValue < oldValue1) {
                        if (value == 49) {
                            firstGift1 = false
                            secondGift1 = true
                            thirdGift1 = true
                        } else if (value == 74) {
                            firstGift1 = false
                            secondGift1 = false
                            thirdGift1 = true
                        }

                    }
                    if (value == 49 && firstGift1) {
                        firstGift1 = false
                        openGiftDialogBox()
                    } else if (value == 74 && secondGift1) {
                        secondGift1 = false
                        openGiftDialogBox()

                    } else if (value == 100 && thirdGift1) {
                        thirdGift1 = false
                        openGiftDialogBox()

                    }
                    oldValue1 = data[possition].enterValue
                }
                else if (possition == 2) {
                    if (data[possition].enterValue < oldValue2) {
                        if (value == 49) {
                            firstGift2 = false
                            secondGift2 = true
                            thirdGift2 = true
                        } else if (value == 74) {
                            firstGift2 = false
                            secondGift2 = false
                            thirdGift2 = true
                        }
                    }
                    if (value == 49 && firstGift2) {
                        firstGift2 = false
                        openGiftDialogBox()
                    } else if (value == 74 && secondGift2) {
                        secondGift2 = false
                        openGiftDialogBox()

                    } else if (value == 100 && thirdGift2) {
                        thirdGift2 = false
                        openGiftDialogBox()

                    }
                    oldValue2 = data[possition].enterValue
                }
                else if (possition == 3) {
                    if (data[possition].enterValue < oldValue3) {
                        if (value == 49) {
                            firstGift3 = false
                            secondGift3 = true
                            thirdGift3 = true
                        } else if (value == 74) {
                            firstGift3 = false
                            secondGift3 = false
                            thirdGift3 = true
                        }

                    }
                    if (value == 49 && firstGift3) {
                        firstGift3 = false
                        openGiftDialogBox()
                    } else if (value == 74 && secondGift3) {
                        secondGift3 = false
                        openGiftDialogBox()

                    } else if (value == 100 && thirdGift3) {
                        thirdGift3 = false
                        openGiftDialogBox()

                    }
                    oldValue3 = data[possition].enterValue
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        calculatorMechanicAdapter.setOnClickListener { item ->
            try {

                valueData = 0.0
                for (i in item) {

                    valueData += i.totalAmount
                }
                val return_val_in_english: String = convert((totalBonus + valueData).toLong())!!
                val strModified = return_val_in_english.capitalize()
            /*    val df = DecimalFormat("#.##")
                val roundoffValueAmount = df.format(valueData)*/

                binding.tvMonthlyAmount.setText(": ₹ ${Utill.change(valueData,2)}")
                binding.tvMonthlyAmountInWord.visibility = View.VISIBLE

                binding.tvMonthlyAmountInWord.text = "${strModified}"

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        calculatorBonusMechanicAdapter.setOnClickListener { bonusPersentage ->
            try {
                totalBonus = ((valueData * bonusPersentage) / 100)

            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }


        binding.ivMoreService.setOnClickListener({
            showBottomSheetDialog()
        })
    }/*
    fun isCheckValueCondition(oldValue: Int,newValue: Int) {
        if (oldValue > newValue && oldValue > 49) {
            firstGift = true
        } else if (oldValue > newValue && oldValue > 74) {
            secondGift = true
        } else if (oldValue > newValue && oldValue > 100) {
            thirdGift = true
        }
    }*/

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val myDrawerView = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)

        val binding = BottomSheetDialogBinding.inflate(layoutInflater,myDrawerView as ViewGroup,false)

        bottomSheetDialog.setContentView(binding.root)
        binding.rvMoreServiceMoreEarningList.apply {
            calculatorBonusMechanicAdapter.updateUserList(homeViewModel.calculatorBonusListData)
            adapter = calculatorBonusMechanicAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }

        binding.ivCancle.setOnClickListener({
            bottomSheetDialog.dismiss()
        })


        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.show()

    }

    fun openGiftDialogBox() {
        Utill.ringSoundFail(this@CalculatorMechanicActivity)
        customAlertDialog.show(this,object : CustomAlertDialog.CustomAlertInterface {

            override fun onCancel() {

                Toast.makeText(this@CalculatorMechanicActivity,"Open Testing",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private val tensNames = arrayOf(""," ten"," twenty"," thirty"," forty"," fifty"," sixty"," seventy"," eighty"," ninety")

    private val numNames = arrayOf("",
        " one",
        " two",
        " three",
        " four",
        " five",
        " six",
        " seven",
        " eight",
        " nine",
        " ten",
        " eleven",
        " twelve",
        " thirteen",
        " fourteen",
        " fifteen",
        " sixteen",
        " seventeen",
        " eighteen",
        " nineteen")

    private fun convertLessThanOneThousand(number: Int): String {
        var number = number
        var soFar: String
        if (number % 100 < 20) {
            soFar = numNames[number % 100]
            number /= 100
        } else {
            soFar = numNames[number % 10]
            number /= 10
            soFar = tensNames[number % 10] + soFar
            number /= 10
        }
        return if (number == 0) soFar else numNames[number] + " hundred" + soFar
    }

    fun convert(number: Long): String? { // 0 to 999 999 999 999
        if (number == 0L) {
            return "zero"
        }
        var snumber = java.lang.Long.toString(number)

        // pad with "0"
        val mask = "000000000000"
        val df = DecimalFormat(mask)
        snumber = df.format(number)

        // XXXnnnnnnnnn
        val billions = snumber.substring(0,3).toInt() // nnnXXXnnnnnn
        val millions = snumber.substring(3,6).toInt() // nnnnnnXXXnnn
        val hundredThousands = snumber.substring(6,9).toInt() // nnnnnnnnnXXX
        val thousands = snumber.substring(9,12).toInt()
        val tradBillions: String
        tradBillions = when (billions) {
            0 -> ""
            1 -> convertLessThanOneThousand(billions) + " billion "
            else -> convertLessThanOneThousand(billions) + " billion "
        }
        var result = tradBillions
        val tradMillions: String
        tradMillions = when (millions) {
            0 -> ""
            1 -> convertLessThanOneThousand(millions) + " million "
            else -> convertLessThanOneThousand(millions) + " million "
        }
        result = result + tradMillions
        val tradHundredThousands: String
        tradHundredThousands = when (hundredThousands) {
            0 -> ""
            1 -> "one thousand "
            else -> convertLessThanOneThousand(hundredThousands) + " thousand "
        }
        result = result + tradHundredThousands
        val tradThousand: String
        tradThousand = convertLessThanOneThousand(thousands)
        result = result + tradThousand

        // remove extra spaces!
        return result.replace("^\\s+".toRegex(),"").replace("\\b\\s{2,}\\b".toRegex()," ")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}