/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react'
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  Button
} from 'react-native'

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions
} from 'react-native/Libraries/NewAppScreen'

import Permission from 'react-native-lk-permission'

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      checkSelfPermission: '',
      checkSelfPermissions: '',
      requestPermission: '',
      requestPermissions: '',
      shouldShowRequestPermissionRationale: '',
      checkFloatWindowPermissions: ''
    }
  }

  componentDidMount() {}

  render() {
    return (
      <View style={styles.containner}>
        <Button
          title="检查单个权限"
          onPress={() => {
            Permission.checkSelfPermission(Permission.RECORD_AUDIO, result => {
              this.setState({
                checkSelfPermission: JSON.stringify(result)
              })
            })
          }}
        />
        <Text>{this.state.checkSelfPermission}</Text>
        <Button
          title="检查权限组"
          onPress={() => {
            Permission.checkSelfPermissions(
              [
                Permission.RECORD_AUDIO,
                Permission.CALL_PHONE,
                Permission.SEND_SMS
              ],
              result => {
                this.setState({
                  checkSelfPermissions: JSON.stringify(result)
                })
              }
            )
          }}
        />
        <Text>{this.state.checkSelfPermissions}</Text>
        <Button
          title="请求权限"
          onPress={() => {
            Permission.requestPermission(Permission.RECORD_AUDIO, result => {
              this.setState({
                requestPermission: JSON.stringify(result)
              })
            })
          }}
        />
        <Text>{this.state.requestPermission}</Text>
        <Button
          title="请求权限组"
          onPress={() => {
            Permission.requestPermissions(
              [
                Permission.RECORD_AUDIO,
                Permission.CALL_PHONE,
                Permission.SEND_SMS
              ],
              result => {
                this.setState({
                  requestPermissions: JSON.stringify(result)
                })
              }
            )
          }}
        />
        <Text>{this.state.requestPermissions}</Text>
        <Button
          title="被拒绝权限状态"
          onPress={() => {
            Permission.shouldShowRequestPermissionRationale(
              Permission.RECORD_AUDIO,
              result => {
                this.setState({
                  shouldShowRequestPermissionRationale: result + ''
                })
              }
            )
          }}
        />
        <Text>{this.state.shouldShowRequestPermissionRationale}</Text>
        <Button
          title="查看悬浮窗状态"
          onPress={() => {
            Permission.checkFloatWindowPermissions(result => {
              this.setState({
                checkFloatWindowPermissions: result + ''
              })
            })
          }}
        />
        <Text>{this.state.checkFloatWindowPermissions}</Text>
        <Button
          title="打开悬浮窗设置"
          onPress={() => {
            Permission.openFloatWindowSetting()
          }}
        />
        <Text />
        <Button
          title="打开设置"
          onPress={() => {
            Permission.openSetting()
          }}
        />
      </View>
    )
  }
}

const styles = StyleSheet.create({
  containner: {
    padding: 15
  },
  mbtn: {
    marginTop: 10
  }
})

export default App
