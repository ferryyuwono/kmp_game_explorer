import SwiftUI
import shared
import KMPObservableViewModelSwiftUI

struct ContentView: View {
    @StateViewModel var viewModel = shared.MainViewModel(
        getGameListUseCase: GetGameListUseCase(
            repository: GameRepositoryImpl()
        )
    )
        
	var body: some View {
        VStack {
            HStack(
                alignment: .center,
                spacing: 8
            ) {
                Button(
                    action: {
                        viewModel.start()
                    }
                ) {
                    Text("Start")
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
            ZStack {
                List {
                    ForEach(
                        viewModel.getGameListSuccessState.data,
                        id: \.self
                    ) { it in
                        Text(it.name).padding()
                    }
                    
                    if !viewModel.getGameListSuccessState.isLastPage {
                        ProgressView()
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                            .foregroundColor(.black)
                            .foregroundColor(.red)
                            .onAppear {
                                viewModel.loadMore()
                            }
                    }
                }.refreshable {
                    viewModel.start()
                }
                
                if viewModel.isShowOnBoardingState {
                    HStack {
                        Image(systemName: "star")
                            .resizable()
                            .frame(width: CGFloat(30), height: CGFloat(30), alignment: .center)
                        Text("Press Start to Load List")
                            .padding()
                    }
                }
                    
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
