import SwiftUI
import shared
import KMPObservableViewModelSwiftUI

struct ContentView: View {
    @StateViewModel var viewModel = shared.MainViewModel()
        
	var body: some View {
        VStack {
            HStack(
                alignment: .center, 
                spacing: 8
            ) {
                Button(
                    action: {
                        viewModel.load()
                    }
                ) {
                    Text("Load")
                        .padding()
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity)
                }
                .background(.blue)
                .cornerRadius(10)
                
                Button(
                    action: {
                        viewModel.reset()
                    }
                ) {
                    Text("Reset")
                        .padding()
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity)
                }
                .background(.red)
                .cornerRadius(10)
            }.padding(10)
            List(viewModel.gameList, id: \.self) { it in
                Text(it)
            }.refreshable {
                viewModel.load()
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
