package com.example.firstproject.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstproject.R
import com.example.firstproject.model.UserModel
import com.example.firstproject.repository.UserRepoImpl
import com.example.firstproject.ui.theme.PurpleGrey80
import com.example.firstproject.viewmodel.UserViewModel
import java.util.Calendar

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        RegisterBody()
        }
    }
}
@Composable
fun RegisterBody(){
    val userViewModel = remember { UserViewModel(UserRepoImpl()) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    var terms by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var showDialog by remember { mutableStateOf(false) }
val activity = context as Activity
    var selectedDate by remember { mutableStateOf("") }
    val datepicker = DatePickerDialog(
        context, { _, year, month, day ->
            selectedDate = "$year/${month + 1}/$day"

        }, year, month, day
    )
    val sharedPreferences = context.getSharedPreferences(
        "User",
        Context.MODE_PRIVATE
    )
    val editor = sharedPreferences.edit()

    val savedEmail = sharedPreferences.getString("email", "")
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(padding)
        ){
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                "Sign Up", style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp)

            )
            OutlinedTextField(
                value = email,
                onValueChange = {data ->
                    email = data
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                placeholder = {
                    Text("Email Address")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PurpleGrey80,
                    unfocusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                )

            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                enabled = false,
                value = selectedDate,
                onValueChange = { data ->
                    selectedDate = data

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{
                        datepicker.show()
                    }
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("DD/MM/YYYY")
                },
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    disabledContainerColor = White,
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier
                .height(20.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                trailingIcon = {
                    IconButton(onClick ={
                        visibility =!visibility
                    } ) {
                        Icon(
                            painter = if(visibility)
                                painterResource(R.drawable.baseline_visibility_off_24 )
                            else
                            painterResource(R.drawable.baseline_visibility_24),
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if(visibility) VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("***********")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PurpleGrey80,
                    unfocusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = terms,
                    onCheckedChange = {
                        terms = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Blue,
                        checkmarkColor = White
                    )
                )
                Text("I agree to terms & conditions")
            }
            Button(
                onClick = {
                    if(!terms) {
                        Toast.makeText(
                            context,
                            "Please agree to terms and Conditions",
                            Toast.LENGTH_SHORT
                        ).show()

                    }else {
                        userViewModel.register(email, password) { success, message, userId ->
                            if (success) {
                                var model = UserModel(
                                    userId = userId,
                                    firstName = "",
                                    lastName = "",
                                    gender = "",
                                    email = email,
                                    dob = selectedDate
                                )
                                userViewModel.addUserToDatabase(
                                    userId,model
                                ) { success, message ->
                                    if (success) {
                                        Toast.makeText(
                                            context,
                                            message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        activity.finish()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                            }
                        }
                    }

//                    if(savedEmail == email){
//                        Toast.makeText(
//                            context,
//                            "Email already registered",
//                            Toast.LENGTH_SHORT
//                        ).show()
//
//                    }
////                        editor.putString("email",email)
//                        editor.putString("password",password)
//                        editor.putString("date",selectedDate)
//                        editor.apply()
//                        Toast.makeText(
//                            context,
//                            "Success",
//                            Toast.LENGTH_SHORT
//                        ).show()

                    val intent = Intent(context, LoginProfile::class.java)
                    context.startActivity(intent)
                    activity.finish()
                          },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(60.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp
                ),

                shape = RoundedCornerShape(10.dp)

            ) {
                Text("Sign Up")
            }
            Text(buildAnnotatedString {
                append("Already have an account? ")
                withStyle(SpanStyle(color = Blue)){
                    append("Sign Up")
                }
            }, modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                )




        }
    }
}



@Preview
@Composable
fun PreviewRegistrationActivity(){
    RegisterBody()
}


