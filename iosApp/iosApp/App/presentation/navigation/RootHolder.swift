//
//  RootHolder.swift
//  iosApp
//
//  Created by Djek Grif on 12/1/26.
//
import SwiftUI
import Shared

class RootHolder: ObservableObject {
    let root: RootComponent
    
    init() {
        root = IosRootFactory().createRoot()
    }
    
}
