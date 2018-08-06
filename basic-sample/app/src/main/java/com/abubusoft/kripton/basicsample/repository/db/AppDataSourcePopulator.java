package com.abubusoft.kripton.basicsample.repository.db;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.SQLitePopulator;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.basicsample.model.Product;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.CommentEntity;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AppDataSourcePopulator implements SQLitePopulator {
    @Override
    public void execute() {

        Logger.info("execute populator");
        // create in async
        BindAppDataSource.getInstance().executeAsync(new BindAppDataSource.Transaction() {
            @Override
            public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<ProductEntity> products = generateProducts();
                for (ProductEntity item : products) {
                    daoFactory.getProductDao().insert(item);
                }

                List<CommentEntity> comments = generateCommentsForProducts(products);
                for (CommentEntity item : comments) {
                    daoFactory.getCommentDao().insert(item);
                }

                Logger.info("finish populator");
                return TransactionResult.COMMIT;
            }
        });

    }


    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine"};
    private static final String[] COMMENTS = new String[]{
            "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6"};

    public static List<ProductEntity> generateProducts() {
        List<ProductEntity> products = new ArrayList<>(FIRST.length * SECOND.length);
        Random rnd = new Random();
        for (int i = 0; i < FIRST.length; i++) {
            for (int j = 0; j < SECOND.length; j++) {
                ProductEntity product = new ProductEntity();
                product.setName(FIRST[i] + " " + SECOND[j]);
                product.setDescription(product.getName() + " " + DESCRIPTION[j]);
                product.setPrice(rnd.nextInt(240));
                product.setId(FIRST.length * i + j + 1);
                products.add(product);
            }
        }
        return products;
    }

    public static List<CommentEntity> generateCommentsForProducts(
            final List<ProductEntity> products) {
        List<CommentEntity> comments = new ArrayList<>();
        Random rnd = new Random();

        for (Product product : products) {
            int commentsNumber = rnd.nextInt(5) + 1;
            for (int i = 0; i < commentsNumber; i++) {
                CommentEntity comment = new CommentEntity();
                comment.setProductId(product.getId());
                comment.setText(COMMENTS[i] + " for " + product.getName());
                comment.setPostedAt(new Date(System.currentTimeMillis()
                        - TimeUnit.DAYS.toMillis(commentsNumber - i) + TimeUnit.HOURS.toMillis(i)));
                comments.add(comment);
            }
        }

        return comments;
    }

}
