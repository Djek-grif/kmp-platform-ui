//
//  StateValue.swift
//  iosApp
//
//  Created by Djek Grif on 19/1/26.
//

import Foundation
import SwiftUI
import Shared
import Combine

@propertyWrapper
public struct StateValue<T: AnyObject>: DynamicProperty {
    @StateObject
    private var observed: ObservedValue<T>
    
    public var wrappedValue: T {
        observed.value
    }
    
    public init(_ value: Value<T>) {
        _observed = StateObject(wrappedValue: ObservedValue(value))
    }
}

private final class ObserverCancellation {
    private let cancelBlock: () -> Void
    
    init(_ cancel: @escaping () -> Void) {
        self.cancelBlock = cancel
    }
    
    func cancel() {
        self.cancelBlock()
    }
}

private final class ObservedValue<T: AnyObject>: ObservableObject {
    @Published
    var value: T
    
    private var cancellation: (any Cancellation)?
    
    init(_ value: Value<T>) {
        self.value = value.value
        
        self.cancellation = value.subscribe { newValue in
            self.value = newValue
        }
    }
    
    deinit {
        cancellation?.cancel()
    }
}

