package non.shahad.quotify.ui.explore


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_explore.*
import non.shahad.quotify.R
import non.shahad.quotify.dagger.Injectable
import non.shahad.quotify.dagger.ViewmodelFactory
import non.shahad.quotify.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import javax.inject.Inject

class ExploreFragment : BaseFragment(), ExploreAdapter.onClickListener ,Injectable{

    private val topicAdapter : ExploreAdapter by lazy { ExploreAdapter(this) }

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var viewmodel : ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProviders.of(this,viewmodelFactory).get(ExploreViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.topics.observe(this, Observer {
            topicAdapter.setTopicList(it)
        })

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        exploreRecycler.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        exploreRecycler.adapter = topicAdapter

    };

    override fun onClick(name: String?) {

    }

    override fun onDestroy() {
        exploreRecycler.adapter = null
        super.onDestroy()
    }


}
