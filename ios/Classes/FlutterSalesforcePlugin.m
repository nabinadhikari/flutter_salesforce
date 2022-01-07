#import "FlutterSalesforcePlugin.h"
#if __has_include(<flutter_salesforce/flutter_salesforce-Swift.h>)
#import <flutter_salesforce/flutter_salesforce-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_salesforce-Swift.h"
#endif

@implementation FlutterSalesforcePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterSalesforcePlugin registerWithRegistrar:registrar];
}
@end
