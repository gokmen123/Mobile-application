package com.example.midtermproject;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;


public class Profile extends Fragment {

    Uri imageUri;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    StorageReference firebaseStorage = FirebaseStorage.getInstance().getReference("uploads/");
    StorageTask storageTask;
    ImageView imgView;
    //1
    View view;
    String names;
    private static final int PICK_IMAGE_REQUEST = 1;
    //1

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        names = getArguments().getString("user");
        imgView= view.findViewById(R.id.img_prof);

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.collection("Users").document(names).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().getBoolean("rememberMe")== true){
                                try {
                                    File localFile = File.createTempFile("temp",".jpg");
                                    StorageReference st = FirebaseStorage.getInstance().getReference("uploads/"+names+".jpg");
                                    st.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            imgView.setImageBitmap(bitmap);
                                            Toast.makeText(getContext(), "Image successfully downloaded", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            }
        }).start();

        // Set an onClick listener for the ImageView

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the image gallery app to select an image in a separate thread
                if(storageTask != null && storageTask.isInProgress()){
                    Toast.makeText(getContext(), "Upload is in progress!", Toast.LENGTH_SHORT).show();
                }
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, PICK_IMAGE_REQUEST);
                        }
                    }).start();
                }
            }
        });
        //2



        TextView username,name,surname,education,email,gender,phoneNo,birthday;
        username=view.findViewById(R.id.username_prof);
        name=view.findViewById(R.id.name_prof);
        surname=view.findViewById(R.id.surname_prof);
        email=view.findViewById(R.id.email_prof);
        education=view.findViewById(R.id.education_prof);
        gender=view.findViewById(R.id.gender_prof);
        phoneNo=view.findViewById(R.id.phone_prof);
        birthday=view.findViewById(R.id.birthday_prof);

        db.collection("Users").document(names).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    username.setText(task.getResult().getString("username"));
                    name.setText(task.getResult().getString("name"));
                    surname.setText(task.getResult().getString("surname"));
                    email.setText(task.getResult().getString("email"));
                    education.setText(task.getResult().getString("education"));
                    gender.setText(task.getResult().getString("sex"));
                    phoneNo.setText(task.getResult().getString("phoneNo"));
                    birthday.setText(task.getResult().getString("dateOfBirth"));
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    //3
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the URI of the selected image
            imageUri = data.getData();

            // Set the image for the ImageView using the URI
            ImageView imgView = getView().findViewById(R.id.img_prof);
            uploadFile();
            imgView.setImageURI(imageUri);
        }
    }
    public String ex(Uri uri){
        ContentResolver cr = view.getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadFile(){
        if(imageUri !=null){
            StorageReference file = firebaseStorage.child(names +".jpg");
            db.collection("Users").document(names).update("rememberMe",true);
            storageTask=file.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Image successfully uploaded", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{
            //user didint pick any pic
        }
    }

    //3
}