package com.dengfx.smart.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dengfx.base.ui.MultiStateViewFragment
import com.dengfx.smart.R
import com.dengfx.smart.databinding.FragmentLibraryDetailBinding
import com.dengfx.smart.databinding.ItemLibraryDetailBinding
import com.dengfx.smart.db.AppDataBase
import com.dengfx.smart.db.Book
import com.dengfx.smart.db.BookRepository
import com.dengfx.smart.vm.LibraryDetailViewModel
import com.dengfx.smart.vm.LibraryDetailViewModelFactory


class LibraryDetailFragment :
    MultiStateViewFragment<LibraryDetailViewModel, FragmentLibraryDetailBinding>(
        R.layout.fragment_library_detail,
        LibraryDetailViewModel::class.java
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding2.recyclerView.adapter = LibraryDetailAdapter().apply {

            addHeaderView(
                BaseViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_library_detail,
                        viewDataBinding2.recyclerView,
                        false
                    )
                ).apply {
                    getView<AppCompatTextView>(R.id.tvBookName).apply {
                        text = "图书名称"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    }
                    getView<AppCompatTextView>(R.id.tvBookLocation).apply {
                        text = "位置"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvBookStatus).apply {
                        text = "状态"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvBookUpdateDate).apply {
                        text = "更新时间"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                }.itemView
            )
        }

        viewModel.books.observe(viewLifecycleOwner, Observer {
            (viewDataBinding2.recyclerView.adapter as LibraryDetailAdapter).data.addAll(it)
        })
    }

    override fun generateViewModel(): LibraryDetailViewModel {
        return LibraryDetailViewModelFactory(
            BookRepository.getInstance(
                AppDataBase.getInstance(attachedContext).bookDao()
            ),
            this,
            this.arguments
        ).create(LibraryDetailViewModel::class.java)
    }
}


class LibraryDetailAdapter :
    BaseQuickAdapter<Book, BaseViewHolder>(R.layout.item_library_detail) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemLibraryDetailBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Book) {
        helper.getBinding<ItemLibraryDetailBinding>()?.book = item
    }
}

