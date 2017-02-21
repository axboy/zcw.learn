#import "SmartConfig.h"
#import <Cordova/CDVPlugin.h>
#import "HFSmartLink.h"
#import "HFSmartLinkDeviceInfo.h"

@interface SmartConfig ()
{
    HFSmartLink * smtlk;
    BOOL isconnecting;
}

@end

@implementation SmartConfig

-(void)pluginInitialize{
    smtlk = [HFSmartLink shareInstence];
    smtlk.isConfigOneDevice = true;
    smtlk.waitTimers = 60;
    isconnecting=false;
}

- (void) hello:(CDVInvokedUrlCommand*)command
{
//    self.currentCallbackId = command.callbackId;
    //    self.currentCallbackId = command.callbackId;
    /*
     *生成订单信息及签名
     */

    //从API请求获取支付信息
    NSMutableDictionary *args = [command argumentAtIndex:0];

    NSString *orderString = [args objectForKey:@"params"];
    NSLog(@"orderString = %@",orderString);

    CDVPluginResult* pluginResult = nil;
    if (orderString != nil && [orderString length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:orderString];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}
- (void) start:(CDVInvokedUrlCommand*)command
{
    //    self.currentCallbackId = command.callbackId;
    /*
     *生成订单信息及签名
     */

    //从API请求获取支付信息
    NSMutableDictionary *args = [command argumentAtIndex:0];


    NSString * ssidStr= [args objectForKey:@"ssid"];
    NSString * pswdStr = [args objectForKey:@"pswd"];
//
//    ssidStr=@"ASAMSUNG-PC";
//    pswdStr=@"1234567890";


    [smtlk startWithSSID:ssidStr Key:pswdStr withV3x:true
            processblock: ^(NSInteger pro) {
                //                    self.progress.progress = (float)(pro)/100.0;
                NSLog(@"orderString = swait");
            } successBlock:^(HFSmartLinkDeviceInfo *dev) {
                //                    [self  showAlertWithMsg:[NSString stringWithFormat:@"%@:%@",dev.mac,dev.ip] title:@"OK22"];
                [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[NSString stringWithFormat:@"%@:%@",dev.mac,dev.ip]] callbackId:command.callbackId];
            } failBlock:^(NSString *failmsg) {
                //                    [self  showAlertWithMsg:failmsg title:@"error12"];
                [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR] callbackId:command.callbackId];
            } endBlock:^(NSDictionary *deviceDic) {
                isconnecting  = false;
                //[self.butConnect setTitle:@"connect" forState:UIControlStateNormal];
            }];
    // [self.butConnect setTitle:@"connecting" forState:UIControlStateNormal];


}
- (void) stop:(CDVInvokedUrlCommand*)command
{
    //    self.currentCallbackId = command.callbackId;
    /*
     *生成订单信息及签名
     */

    //从API请求获取支付信息
    NSMutableDictionary *args = [command argumentAtIndex:0];

    [smtlk stopWithBlock:^(NSString *stopMsg, BOOL isOk) {
       if(isOk){

                //                [self.butConnect setTitle:@"1connect" forState:UIControlStateNormal];
//          [self showAlertWithMsg:stopMsg title:@"OK34"];
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:stopMsg] callbackId:command.callbackId];
       }else{
               [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR] callbackId:command.callbackId];
       }
   }];
}

- (IBAction)swPressed:(id)sender {
//    if(self.switcher.on){
//        smtlk.isConfigOneDevice = true;
//    }else{
//        smtlk.isConfigOneDevice = false;
//    }
}

-(void)showAlertWithMsg:(NSString *)msg
                  title:(NSString*)title{
    UIAlertView * alert = [[UIAlertView alloc]initWithTitle:title message:msg delegate:nil cancelButtonTitle:@"cancel" otherButtonTitles:@"ok", nil];
    [alert show];
}

-(void)savePswd{
    NSUserDefaults * def = [NSUserDefaults standardUserDefaults];

}
-(NSString *)getspwdByssid:(NSString * )mssid{
    NSUserDefaults * def = [NSUserDefaults standardUserDefaults];
    return [def objectForKey:mssid];
}

- (void)showWifiSsid
{
    BOOL wifiOK= FALSE;
    NSDictionary *ifs;
    NSString *ssid;
    UIAlertView *alert;
    if (!wifiOK)
    {
        ifs = [self fetchSSIDInfo];
        ssid = [ifs objectForKey:@"SSID"];
        if (ssid!= nil)
        {
            wifiOK= TRUE;
//            self.txtSSID.text = ssid;
        }
        else
        {
            alert= [[UIAlertView alloc] initWithTitle:@"" message:[NSString stringWithFormat:@"请连接Wi-Fi"] delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:nil];
            alert.delegate=self;
            [alert show];
        }
    }
}

- (id)fetchSSIDInfo {
//    NSArray *ifs = (__bridge_transfer id)CNCopySupportedInterfaces();
//    NSLog(@"Supported interfaces: %@", ifs);
//    id info = nil;
//    for (NSString *ifnam in ifs) {
//        info = (__bridge_transfer id)CNCopyCurrentNetworkInfo((__bridge CFStringRef)ifnam);
//        NSLog(@"%@ => %@", ifnam, info);
//        if (info && [info count]) { break; }
//    }
    return @"111";
}

- (BOOL)textFieldShouldReturn:(UITextField *)theTextField {
    [theTextField resignFirstResponder];
    return YES;
}


@end
