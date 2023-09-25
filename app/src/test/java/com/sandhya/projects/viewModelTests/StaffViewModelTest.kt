package com.sandhya.projects.viewModelTests
/*
package com.sandhya.projects.viewModelTests

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sandhya.projects.Wand
import com.sandhya.projects.staff.data.StaffData
import com.sandhya.projects.staff.data.StaffRepository
import com.sandhya.projects.staff.ui.StaffViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.json.JSONArray
import org.json.JSONException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class StaffViewModelTest {

    @Mock
    private lateinit var staffRepository: StaffRepository

    private lateinit var staffViewModel: StaffViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        staffViewModel = StaffViewModel(staffRepository)
    }

    @Test
    fun fetchInitialStaff_success() = runTest {
        val jsonString = getJsonFromAssets("staffResponse.json")
        val mockStaffData = parseJsonToStaffList(jsonString)
        `when`(staffRepository.getStaffData()).thenReturn(mockStaffData)

        val staffViewModel = StaffViewModel(staffRepository)

        staffViewModel.fetchInitialStaff()
        delay(500)

        assertNotNull(staffViewModel.staffList.value)
        assertEquals(mockStaffData, staffViewModel.staffList.value)
        assertEquals(false, staffViewModel.isLoading.value)
    }

    @Test
    fun fetchInitialStaff_failure() = runTest {
        // Arrange
        val errorMessage = "Network error"
        `when`(staffRepository.getStaffData()).thenThrow(RuntimeException(errorMessage))

        // Act
        staffViewModel.fetchInitialStaff()

        // Assert
        assertEquals(null, staffViewModel.staffList.value)
        assertEquals(false, staffViewModel.isLoading.value)
    }

    @Test
    fun fetchRemainingStaff_success() = runTest {
        // Arrange
        val initialData = createMockStaffDataList(20)
        val remainingData = createMockStaffDataList(5)
        val updatedList = initialData + remainingData
        val mockResponse = Response.success(remainingData)
        `when`(staffRepository.getStaffData()).thenReturn(mockResponse)

        staffViewModel.staffList.value = initialData

        // Act
        staffViewModel.fetchRemainingStaff()

        // Assert
        assertEquals(updatedList, staffViewModel.staffList.value)
        assertEquals(false, staffViewModel.isLoading.value)
    }

    @Test
    fun fetchRemainingStaff_failure() = runTest {
        // Arrange
        val errorMessage = "Network error"
        `when`(staffRepository.getStaffData()).thenThrow(RuntimeException(errorMessage))

        // Act
        staffViewModel.fetchRemainingStaff()

        // Assert
        assertEquals(null, staffViewModel.staffList.value)
        assertEquals(false, staffViewModel.isLoading.value)
    }

    private fun createMockStaffDataList(size: Int): List<StaffData> {
        val staffDataList = mutableListOf<StaffData>()
        for (i in 0 until size) {
            staffDataList.add(
                StaffData(
                    id = "id_$i",
                    name = "Staff $i",
                    alternate_names = emptyList(),
                    species = "species",
                    gender = "gender",
                    house = "house",
                    dateOfBirth = "dateOfBirth",
                    yearOfBirth = 1990,
                    wizard = true,
                    ancestry = "ancestry",
                    eyeColour = "eyeColour",
                    hairColour = "hairColour",
                    wand = createMockWand(),
                    patronus = "patronus",
                    hogwartsStudent = true,
                    hogwartsStaff = true,
                    actor = "actor",
                    alternate_actors = emptyList(),
                    alive = true,
                    image = "image"
                )
            )
        }
        return staffDataList
    }

    private fun createMockWand(): Wand {
        return Wand(
            core = "core", wood = "wood", length = 10.0f
        )
    }

    private fun getJsonFromAssets(fileName: String): String {
        return try {
            val inputStream = InstrumentationRegistry.getInstrumentation().context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    private fun parseJsonToStaffList(jsonString: String): Response<List<StaffData>> {
        return try {
            val jsonArray = JSONArray(jsonString)
            val staffList = mutableListOf<StaffData>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val species = jsonObject.getString("species")
                val gender = jsonObject.getString("gender")
                val house = jsonObject.getString("house")
                val dateOfBirth = jsonObject.getString("dateOfBirth")
                val yearOfBirth = jsonObject.getString("yearOfBirth")
                val isWizard = jsonObject.getInt("wizard")
                val ancestry = jsonObject.getBoolean("ancestry")
                val eyeColour = jsonObject.getString("eyeColour")
                val hairColour = jsonObject.getString("hairColour")
                val wandObj = jsonObject.getJSONObject("wand")
                val wood = wandObj.getString("wood")
                val core = wandObj.getString("core")
                val length = wandObj.getDouble("length")
                val patronus = jsonObject.getString("patronus")
                val isHogwartsStudent = jsonObject.getBoolean("hogwartsStudent")
                val isHogwartsStaff = jsonObject.getBoolean("hogwartsStaff")
                val actor = jsonObject.getString("actor")
                val alive = jsonObject.getBoolean("alive")
                val image = jsonObject.getString("image")

                val staff = StaffData(
                    id,
                    name,
                    species,
                    gender,
                    house,
                    dateOfBirth,
                    yearOfBirth,
                    isWizard,
                    ancestry,
                    eyeColour,
                    hairColour,
                    wood,
                    core,
                    length,
                    patronus,
                    isHogwartsStudent,
                    isHogwartsStaff,
                    actor,
                    alive,
                    image
                )
                staffList.add(staff)
            }

            Response.success(staffList)
        } catch (e: JSONException) {
            e.printStackTrace()
            Response.error(e.message)
        }
    }
}*/
