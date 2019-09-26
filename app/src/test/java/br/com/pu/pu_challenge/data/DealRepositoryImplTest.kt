package br.com.pu.pu_challenge.data

import br.com.pu.pu_challenge.data.database.AppDatabase
import br.com.pu.pu_challenge.data.network.PUApi
import br.com.pu.pu_challenge.data.network.remote.DealRemote
import br.com.pu.pu_challenge.data.network.remote.ImagesRemote
import br.com.pu.pu_challenge.data.network.remote.PartnerRemote
import br.com.pu.pu_challenge.data.network.remote.ResponseRemote
import br.com.pu.pu_challenge.domain.entity.Deal
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import java.io.IOException

/**
 * Local unit test for the [DealRepositoryImpl], mocking the [PUApi] dependency.
 */
class DealRepositoryImplTest {

    private var dealRepositoryImpl : DealRepositoryImpl? = null

    private lateinit var api : PUApi

    private lateinit var db : AppDatabase

    @Before
    fun setUp() {
        // A little hack to avoid the mock of the Call interface
        api = Mockito.mock(PUApi::class.java, Mockito.RETURNS_DEEP_STUBS)
        db = Mockito.mock(AppDatabase::class.java, Mockito.RETURNS_DEEP_STUBS)
        dealRepositoryImpl = DealRepositoryImpl(api, db)
        Assert.assertNotNull(dealRepositoryImpl)
    }

    @Test
    fun `test deals retrieve works as expected`() {
        val responseRemoteMock = ResponseRemote(200,
            listOf(DealRemote("someId", listOf(ImagesRemote("someThumbUrl")),
                PartnerRemote("someTitle"), "someDescription", 100.0.toFloat())))

        val response = Response.success(responseRemoteMock)

        val dealsExpected = listOf(Deal("someId", listOf("someThumb"), "someTitle",
            "someDescription", 100.0.toFloat()))

        Mockito.`when`(api.retrieveDeals().execute()).thenReturn(response)
        val pagedList = dealRepositoryImpl!!.getDeals()

        Assert.assertEquals(dealsExpected, pagedList.snapshot())
    }

    @Test(expected = IOException::class)
    fun `test the server returned some status error`() {
        val response : Response<ResponseRemote>
                = Response.error(500, ResponseBody.create(null, "someContent"))

        Mockito.`when`(api.retrieveDeals().execute()).thenReturn(response)
        val dealsReturn = dealRepositoryImpl!!.getDeals()
    }

    @Test(expected = IOException::class)
    fun `test the app cannot connect with the server`() {
        Mockito.`when`(api.retrieveDeals().execute()).thenThrow(IOException::class.java)

        val dealsReturn = dealRepositoryImpl!!.getDeals()
    }
}