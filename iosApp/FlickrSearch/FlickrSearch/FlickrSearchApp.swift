//
//  FlickrSearchApp.swift
//  FlickrSearch
//
//  Created by Niek Akerboom on 11.03.25.
//

import SwiftUI
import FlickrSearchKmp

@main
struct FlickrSearchApp: App {
    init() {
        // Initialize Koin and Napier
        KoinHelperKt.initializeHelpers()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
