<?php

require_once(Mage::getModuleDir('controllers','Mage_Catalog').DS.'ProductController.php');

class Hive_Test_ProductController extends Mage_Catalog_ProductController
{
	/**
     * Product view action
     */
    public function viewAction()
    {
        // Get initial data from request
        $categoryId = (int) $this->getRequest()->getParam('category', false);
        $productId  = (int) $this->getRequest()->getParam('id');
        $specifyOptions = $this->getRequest()->getParam('options');

        // Prepare helper and params
        $viewHelper = Mage::helper('catalog/product_view');

        $params = new Varien_Object();
        $params->setCategoryId($categoryId);
        $params->setSpecifyOptions($specifyOptions);

        $product = Mage::getModel('catalog/product')->load($productId);  


        $file = '/var/www/magento/demo.txt';
        // Open the file to get existing content
        $current = file_get_contents($file);

        $date = date('Y-m-d h:i:s', time());
        // Append a new person to the file
        $current .= $productId . "," . $product->getName() . "," . $date . "\n";
        // Write the contents back to the file
        file_put_contents($file, $current);

        // Render page
        try {
            $viewHelper->prepareAndRender($productId, $this, $params);
        } catch (Exception $e) {
            if ($e->getCode() == $viewHelper->ERR_NO_PRODUCT_LOADED) {
                if (isset($_GET['store'])  && !$this->getResponse()->isRedirect()) {
                    $this->_redirect('');
                } elseif (!$this->getResponse()->isRedirect()) {
                    $this->_forward('noRoute');
                }
            } else {
                Mage::logException($e);
                $this->_forward('noRoute');
            }
        }
    }
}