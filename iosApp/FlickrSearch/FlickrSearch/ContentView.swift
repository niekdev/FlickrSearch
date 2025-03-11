//
//  ContentView.swift
//  FlickrSearch
//
//  Created by Niek Akerboom on 11.03.25.
//

import SwiftUI
import FlickrSearchKmp

struct ComposeView: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> some UIViewController {
        return KmpViewControllerKt.create()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        // no-op
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard)
    }
}

#Preview {
    ContentView()
}
