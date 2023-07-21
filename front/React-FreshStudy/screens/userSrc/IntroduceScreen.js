import React from 'react';
import { StyleSheet, View, Text, ScrollView } from 'react-native';


function IntroduceScreen() {
    return (
        <ScrollView contentContainerStyle={styles.container}>

            <Text style={styles.textTitle}>
                CGLH는{"\n "}
                영농폐기물 파쇄 신청 어플입니다. 
            </Text>

            <Text style={styles.subTitle}>영농 페기물이란?</Text>

            <Text style={styles.text}>
                영농폐기물은 농업과 관련된 폐기물로, 폐비닐류와 폐농약용기류를 제외한 모든 영농폐기물을 포함합니다. {"\n"}
                이러한 폐기물은 환경에 해로울 수 있으며 적절한 처리가 필요합니다. CGLH는 영농폐기물을 파쇄하여 처리할 수 있도록 도와줍니다.
            </Text>

            <Text style={styles.subTitle}>
                CGLH 사용 이점!
            </Text>

            <Text style={styles.text}>
                - 환경 보호 - {"\n"} 
                영농폐기물은 잘 처리되지 않으면 토양과 물에 오염을 일으킬 수 있습니다. 
                CGLH를 사용하여 폐기물을 파쇄함으로써 환경 오염을 최소화하고 지속 가능한 농업을 도모할 수 있습니다.{"\n\n"}
            
                - 편리한 신청 절차 - {"\n"} 
                CGLH를 간단하고 편리한 신청 절차를 제공합니다. 사용자들은 몇 가지 간단한 단계를 따라 영농폐기물 파쇄 신청을 할 수 있으며, 복잡한 절차나 서류 제출을 걱정할 필요가 없습니다.{"\n\n"}
                
                - 시간과 비용 절약 - {"\n"} 
                영농폐기물을 파쇄하여 처리하는 것은 기존의 방법보다 더욱 효율적이며, 시간과 비용을 절약할 수 있습니다. 이는 농업 생산성을 향상시키고 농부들의 경제적 부담을 줄여줍니다.{"\n\n"}
            
                - 데이터 관리와 추적 - {"\n"} 
                CGLH를 영농폐기물 파쇄 신청과 처리과정을 체계적으로 관리하고 추적할 수 있습니다. 이를 통해 데이터 분석을 통한 개선점 도출과 효율적인 운영이 가능합니다.{"\n\n"}

                CGLH를 사용하여 영농폐기물 처리를 간편하고 환경에 친화적인 방향으로 진행해보세요. 함께하는 작은 노력이 큰 환경 보호에 기여할 수 있습니다.
            </Text>
        </ScrollView>
    )
}

const styles = StyleSheet.create({ 
    container: {
        flexGrow: 1,
        //backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 20,
    },
    textTitle: {
        fontSize: 20,
        color: 'black',
        textAlign: 'center',
        lineHeight: 28,
        backgroundColor: 'white',
        margin: 10,
        fontWeight: 'bold',
    },
    subTitle: {
        fontSize: 19,
        color: 'black',
        textAlign: 'center',
        lineHeight: 28,
        backgroundColor: 'white',
        marginBottom: -6,
        fontWeight: 'bold',
    },
    text: {
        fontSize: 17,
        color: 'black',
        textAlign: 'center',
        lineHeight: 28,
        backgroundColor: 'white',
        margin: 10,
    },
})


export default IntroduceScreen;