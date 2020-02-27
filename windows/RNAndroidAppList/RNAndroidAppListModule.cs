using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Android.App.List.RNAndroidAppList
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNAndroidAppListModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNAndroidAppListModule"/>.
        /// </summary>
        internal RNAndroidAppListModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNAndroidAppList";
            }
        }
    }
}
