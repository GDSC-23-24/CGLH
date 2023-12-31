import React, {useState} from 'react'
import { View, StyleSheet, TouchableOpacity, TextInput,Picker, ImageBackground} from 'react-native';
import DateTimePickerModal from "react-native-modal-datetime-picker";


Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

function DateScreen({onDateChange, selectedDate}) {
    const placeholder = "날짜를 입력해주세요";

    const [isDatePickerVisible, setDatePickerVisibility] = useState(false);
    // const [text, onChangeText] = useState("");
    
    const showDatePicker = () => {
        setDatePickerVisibility(true);
    };

    const hideDatePicker = () => {
        setDatePickerVisibility(false);
    };
    
    const handleConfirm = (date) => {
        hideDatePicker();
        onDateChange(date)
    };

    return (
        <View style={styles.container}>
          <TouchableOpacity onPress={showDatePicker}>
            <TextInput
              pointerEvents="none"
              style={styles.textInput}
              placeholder={placeholder}
              placeholderTextColor='white'
              underlineColorAndroid="transparent"
              editable={false}
              value={selectedDate === null ? null : selectedDate.format("yyyy/MM/dd")}
            />
            <DateTimePickerModal
              headerTextIOS={placeholder}
              isVisible={isDatePickerVisible}
              mode="date"
              onConfirm={handleConfirm}
              onCancel={hideDatePicker}
            />
          </TouchableOpacity>
        </View>
      );
    }
    
    const styles = StyleSheet.create({
      container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        //padding: 20,
      },
      textInput: {
        fontSize: 20,
        color: 'white',
        backgroundColor: '#4B8A08',
        borderRadius: 10, // Add border radius for a rounded look
        paddingVertical: 12, // Adjust vertical padding for better spacing
        paddingHorizontal: 16, // Adjust horizontal padding for better spacing
      },
    })

export default DateScreen;