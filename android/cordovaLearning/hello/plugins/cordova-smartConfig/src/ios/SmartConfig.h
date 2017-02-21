#import <Cordova/CDVPlugin.h>

@interface SmartConfig : CDVPlugin

- (void) hello:(CDVInvokedUrlCommand*)command;
- (void) start:(CDVInvokedUrlCommand*)command;
- (void) stop:(CDVInvokedUrlCommand*)command;
@end
