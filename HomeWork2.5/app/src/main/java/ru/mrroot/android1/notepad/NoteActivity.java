package ru.mrroot.android1.notepad;  //SettingsFragment

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class NoteActivity extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_settings, container, false);
            initView(view);
            return view;
        }

        private void initView(View view) {
            initSwitchBackStack(view);
            initRadioAdd(view);
            initRadioReplace(view);
            initSwitchBackAsRemove(view);
            initSwitchDeleteBeforeAdd(view);
        }

        private void initRadioReplace(View view) {
            RadioButton radioButtonReplace = view.findViewById(R.id.radioButtonReplace);
            radioButtonReplace.setChecked(!Note.IsAddFragment);
            radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note.IsAddFragment = !isChecked;
                    writeSettings();
                }
            });
        }

        private void initRadioAdd(View view) {
            RadioButton radioButtonAdd = view.findViewById(R.id.radioButtonAdd);
            radioButtonAdd.setChecked(Note.IsAddFragment);
            radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note.IsAddFragment = isChecked;
                    writeSettings();
                }
            });
        }

        private void initSwitchBackStack(View view) {
            // Элемент пользовательского интерфейса - переключатель
            // По функционалу очень похож на CheckBox, но имеет другой дизайн
            SwitchCompat switchUseBackStack = view.findViewById(R.id.switchBackStack);
            switchUseBackStack.setChecked(Note.IsBackStack);
            switchUseBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note.IsBackStack = isChecked;
                    writeSettings();
                }
            });
        }

        private void initSwitchBackAsRemove(View view) {
            SwitchCompat switchBackAsRemove = view.findViewById(R.id.switchBackAsRemove);
            switchBackAsRemove.setChecked(Note.IsBackAsRemove);
            switchBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note.IsBackAsRemove = isChecked;
                    writeSettings();
                }
            });
        }

        private void initSwitchDeleteBeforeAdd(View view) {
            SwitchCompat switchDeleteBeforeAdd = view.findViewById(R.id.switchDeleteBeforeAdd);
            switchDeleteBeforeAdd.setChecked(Note.IsDeleteBeforeAdd);
            switchDeleteBeforeAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note.IsDeleteBeforeAdd = isChecked;
                    writeSettings();
                }
            });
        }

        // Сохранение настроек приложения
        private void writeSettings(){
            // Специальный класс для хранения настроек
            SharedPreferences sharedPref = requireActivity().getSharedPreferences(Note.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            // Настройки сохраняются посредством специального класса editor.
            SharedPreferences.Editor editor = sharedPref.edit();
            // Задаем значения настроек
            editor.putBoolean(Note.IS_BACK_STACK_USED, Note.IsBackStack);
            editor.putBoolean(Note.IS_ADD_FRAGMENT_USED, Note.IsAddFragment);
            editor.putBoolean(Note.IS_BACK_AS_REMOVE_FRAGMENT, Note.IsBackAsRemove);
            editor.putBoolean(Note.IS_DELETE_FRAGMENT_BEFORE_ADD, Note.IsDeleteBeforeAdd);
            // Сохраняем значения настроек
            editor.apply();
        }
    }
