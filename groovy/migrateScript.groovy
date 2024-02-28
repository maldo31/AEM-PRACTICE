import org.apache.sling.settings.SlingSettingsService

QUERY_TEMPLATE = "SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE([%s]) " +
        " AND [sling:resourceType]='%s'"
RESOURCE_TYPE ="myproject/components/inner"
WRAPPER_NAME = "inner-wrapper"
ROOT_PATH = "/"

HEADER = "Old Path, New Path"



def migratePages(String rootPath) {
    String query = String.format(QUERY_TEMPLATE, rootPath, RESOURCE_TYPE)
    def movedPages = []
    def movedPagesOld = []
    List movedPagesLog = new ArrayList();
    resourceResolver.findResources(query, "JCR-SQL2").each {
        Node itNode = it.adaptTo(Node.class)
        def itOldPath = itNode.getPath()
        parentNode = itNode.getParent()
        def wrapperNode = parentNode.addNode(WRAPPER_NAME)
        wrapperNode.setPropert("sling:resourceType","myproject/components/wrapper")
        parentNode.orderBefore(WRAPPER_NAME)
        session.move(itNode.getPath(),wrapperNode.getPath())
        movedPages.add(itNode.getPath())
        movedPagesOld.add(itOldPath)
        movedPagesLog.add([itOldPath, itNode.getPath()])
    }
    session.save()

        for(path in movedPages){
            activate(path)
        }
        for(path in movedPagesOld){
            deactivate(path)
        }
    session.save()

    println("Migrated pages: ")
    println(HEADER)
    movedPages.each {
        println(it)
    }

    }
Set<String> runModes = getService(SlingSettingsService.class).getRunModes()
def isAuthor = runModes.contains("author")
if(isAuthor){
    migratePages(ROOT_PATH)
}
