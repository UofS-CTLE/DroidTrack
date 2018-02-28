package edu.scranton.ctleweb.droidtrack.projtrack

import android.util.Log
import edu.scranton.ctleweb.droidtrack.ProjtrackService
import edu.scranton.ctleweb.droidtrack.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoaderThread constructor(private val token: String) : Thread() {

    private val sg = ServiceGenerator.createService(ProjtrackService::class.java, token)

    override fun run() {
        depts()
        types()
        clients()
        my_projects()
        all_projects()
    }

    private fun depts() {
        if (Content.DEPTS.size == 0) {
            val depts = sg.getDepartments(token)

            depts.enqueue(object : Callback<List<Content.DepartmentItem>> {
                override fun onResponse(call: Call<List<Content.DepartmentItem>>,
                                        response: Response<List<Content.DepartmentItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        Content.DEPTS.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("DEPTS ERRBODY", response.errorBody()!!.string())
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Content.DepartmentItem>>?, t: Throwable?) {
                    Log.d("DeptDownloadFail", t.toString())
                }
            })
        }
    }

    private fun types() {
        if (Content.DEPTS.size == 0) {
            val types = sg.getTypes(token)

            types.enqueue(object : Callback<List<Content.TypeItem>> {
                override fun onResponse(call: Call<List<Content.TypeItem>>,
                                        response: Response<List<Content.TypeItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        Content.TYPES.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("TYPES ERRBODY", response.errorBody()!!.string())
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Content.TypeItem>>?, t: Throwable?) {
                    Log.d("TypeDownloadFail", t.toString())
                }
            })
        }
    }

    private fun clients() {
        if (Content.CLIENTS.size == 0) {
            val clients = sg.getClients(token)

            clients.enqueue(object : Callback<List<Content.ClientItem>> {
                override fun onResponse(call: Call<List<Content.ClientItem>>,
                                        response: Response<List<Content.ClientItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        Content.CLIENTS.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("CLNTS ERRBODY", response.errorBody()!!.string())
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Content.ClientItem>>?, t: Throwable?) {
                    Log.d("ClientDownloadFail", t.toString())
                }
            })
        }
    }

    private fun my_projects() {
        if (MyProjectsContent.ITEMS.size == 0) {
            val projects = sg.getMyProjects(token)
            projects.enqueue(object : Callback<List<MyProjectsContent.ProjectItem>> {
                override fun onResponse(call: Call<List<MyProjectsContent.ProjectItem>>,
                                        response: Response<List<MyProjectsContent.ProjectItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        MyProjectsContent.ITEMS.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("PJCTS ERRBODY", response.errorBody()!!.string())
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MyProjectsContent.ProjectItem>>,
                                       t: Throwable) {
                    Log.d("MYDownloadFail", t.toString())
                }
            })
        }
    }

    private fun all_projects() {
        if (AllProjectsContent.ITEMS.size == 0) {
            val projects = sg.getAllProjects(token)
            projects.enqueue(object : Callback<List<AllProjectsContent.ProjectItem>> {
                override fun onResponse(call: Call<List<AllProjectsContent.ProjectItem>>,
                                        response: Response<List<AllProjectsContent.ProjectItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        AllProjectsContent.ITEMS.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("PROJS ERRBODY", response.errorBody()!!.string())
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<AllProjectsContent.ProjectItem>>,
                                       t: Throwable) {
                    Log.d("ALLDownloadFail", t.toString())
                }
            })
        }
    }
}