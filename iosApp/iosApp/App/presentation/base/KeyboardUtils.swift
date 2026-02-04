//
//  KeyboardUtils.swift
//  iosApp
//
//  Created by Djek Grif on 4/2/26.
//

import UIKit

func hideKeyboard() {
    UIApplication.shared.sendAction(
        #selector(UIResponder.resignFirstResponder),
        to: nil,
        from: nil,
        for: nil
    )
}
