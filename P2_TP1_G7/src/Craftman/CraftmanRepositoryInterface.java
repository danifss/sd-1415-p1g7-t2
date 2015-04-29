package Craftman;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public interface CraftmanRepositoryInterface {

    /**
     * Set state of the Craftman[i].
     *
     * @param craftmanId Craftman id
     * @param state State of the Craftman
     */
    void setCraftmanState(int craftmanId, int state);

    /**
     * Change the number of products (accumulation) manufactured by the craftsman[i].
     *
     * @param craftmanId Craftman id
     * @param nGoodsCraftedByCraftman total number of the products crafted by the Craftman
     */
    void setnGoodsCraftedByCraftman(int craftmanId, int nGoodsCraftedByCraftman);

}
